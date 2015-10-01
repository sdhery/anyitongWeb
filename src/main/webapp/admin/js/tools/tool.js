var $W = window.top
var $dialog;
var $iframeDialog;
var $approvalFormDialog;
var isApproval = false
function openDialog(title, url, gridId, width, height) {
    $dialog = $W.$("<div></div>").dialog({
        title: title,
        href: url,
        modal: true,
        width: "500",
        height: "500",
        onClose: function () {
            $W.$(this).window("window").nextAll('.window-shadow,.window-mask').remove()
            $W.$(this).window("window").remove()
        }
    })
}
function locationUrl(title, url) {
    window.location.href = url
}
function openIframeDialog(title, url, gridId, width, height, buttons) {
    if (!buttons || buttons == "null") {
        buttons = [{
            text: '确认',
            iconCls: 'icon-ok',
            handler: function () {
                $W.dialogIFrame.submitForm()
            }
        }, {
            text: '关闭',
            iconCls: 'icon-cancel',
            handler: function () {
                $iframeDialog.window("close")
            }
        }]
    }
    $iframeDialog = $W.$("<div style='overflow:hidden'></div>").dialog({
        title: title,
        width: width ? width : "auto",
        height: height ? height : "auto",
        maximizable:true,
        content: "<iframe scrolling=\"auto\" frameborder=\"0\" style=\"width:100%;height:99.8%;\" name=\"dialogIFrame\" id=\"dialogIFrame\" src='" + url + "'></iframe>",
        modal: true,
        onBeforeClose:showApprovalConfirm,
        onClose: function () {
            $W.$(this).window("window").nextAll('.window-shadow,.window-mask').remove()
            $W.$(this).window("window").remove()
        },
        buttons: buttons
    })
}
function showApprovalConfirm(){
    var flag = true
    if (isApproval) {
        flag = false
        $.messager.confirm('系统消息', '您是否编辑完成，请点击编辑完成按钮，再自动关闭窗口?', function (r) {
            if (r) {
            }
        })
    }
    return flag
}
function reloadContentGird() {
    $W.contentIFrame.reloadGird()
}
function closeDialog() {
    $iframeDialog.window("close")
}
function showMessage(msg, time) {
    $.messager.show({
        title: '系统消息',
        msg: msg,
        timeout: time ? time : 1800,
        style: {
            right: '',
            top: document.body.scrollTop + document.documentElement.scrollTop,
            bottom: ''
        }
    });
}
/**
 * 提示信息
 */
function tip(msg) {
    $.messager.alert('系统提示', msg, 'info');
}
function updateIframeDialog(title, url, gridId, width, height, idFiledName) {
    var rowsData = $W.contentIFrame.$("#" + gridId).datagrid('getSelected');
    if (rowsData == null) {
        tip('请选择编辑项目');
        return;
    }
    url += '/' + rowsData[idFiledName]
    openIframeDialog(title, url, gridId, width, height)
}
//打印
function printUrl(title, url, gridId, width, height, idFiledName) {
    var rowsData = $W.contentIFrame.$("#" + gridId).datagrid('getSelected');
    if (rowsData == null) {
        tip('请选择要打印的登记人');
        return;
    }
    url += '/' + rowsData[idFiledName]
    $W.open(url)
}
//地图
function map(title, url, gridId, width, height, idFiledName) {
    var rowsData = $W.contentIFrame.$("#" + gridId).datagrid('getSelected');
    if (rowsData == null) {
        tip('请选择要查看的登记人');
        return;
    }
    url += '/' + rowsData[idFiledName]
    $W.open(url)
}
//审批
function approval(title, url, gridId, width, height, idFiledName) {
    var rowsData = $W.contentIFrame.$("#" + gridId).datagrid('getSelected');
    if (rowsData == null) {
        tip('请选择要编辑的登记人');
        return;
    }
    var approvalStatus = rowsData["approvalStatus"]
    if (approvalStatus > 2) {
        tip('你选择的登记人已经编辑了');
        return
    }
    if ((approvalStatus == 2 && rowsData.approvalSysUserId != $.adminId) && $.adminId != 1) {
        tip('你选择的登记人正在编辑');
        return
    }
    url += '/' + rowsData[idFiledName] + "?editing=true"
    isApproval = true
    var buttons = [{
        text: '编辑完成',
        iconCls: 'icon-ok',
        handler: function () {
            /*$.messager.confirm('My Title', 'Are you confirm this?', function(r){
             if (r){
             alert('confirmed: '+r);
             }
             });*/
            var params = new Object();
            params["customerId"] = rowsData[idFiledName];
            params["approvalSysUserId"] = $.adminId;
            params["approvalStatus"] = 3;
            $.messager.progress({
                title: '请耐心等待',
                msg: '正在提交中...'
            });
            $.post($.frontPath + '/admin/customer/approval', params, function (resultObj) {
                $.messager.progress('close');
                isApproval = false
                var tip = "成功"
                if (!resultObj.success) {
                    isApproval = true
                    tip = "失败"
                }
                showMessage("审核登记通过" + tip)
                reloadContentGird();
                closeDialog()
            })
        }
    }, {
        text: '审批不通过',
        iconCls: 'icon-remove',
        handler: function () {
            $approvalFormDialog = $W.$("<div></div>").dialog({
                title: "请输入审核原因",
                content: "<form id='approvalForm'><input type='hidden' name='customerId' value='" + rowsData[idFiledName] + "'/><input type='hidden' name='approvalSysUserId' value='" + $.adminId + "'/><input type='hidden' name='approvalStatus' value='4'/><table cellpadding='5'><tr><td valign='top'>审核原因:</td><td><input class='easyui-textbox' data-options='multiline:true,required:true' style='width:280px;height:220px' name='approvalDes'/></td></tr></table></form>",
                modal: true,
                onClose: function () {
                    $W.$(this).window("window").nextAll('.window-shadow,.window-mask').remove()
                    $W.$(this).window("window").remove()
                },
                buttons: [{
                    text: '保存',
                    iconCls: 'icon-save',
                    handler: function () {
                        $("#approvalForm").submit();
                    }
                }, {
                    text: '关闭',
                    iconCls: 'icon-cancel',
                    handler: function () {
                        $approvalFormDialog.dialog("close")
                    }
                }],
                onOpen: function () {
                    initApprovalForm()
                }

            })
        }
    }, {
        text: '关闭',
        iconCls: 'icon-cancel',
        handler: function () {
            $iframeDialog.window("close")
        }
    }]
    title = title + ",<span style='color:red'>编辑人:" + $.adminRealName + "</span>"
    openIframeDialog(title, url, gridId, width, height, buttons)
}
function initApprovalForm() {
    $("#approvalForm").form({
        url: $.frontPath + '/admin/customer/approval',
        onSubmit: function () {
            var flag = $(this).form('validate');
            return flag;
        },
        success: function (resultObj) {
            var tip = "成功"
            isApproval = false
            resultObj = $.parseJSON(resultObj)
            if (!resultObj.success) {
                isApproval = true
                tip = "失败"
            }
            showMessage("编辑" + tip)
            reloadContentGird();
            $approvalFormDialog.dialog("close")
            closeDialog()
        }
    })
}