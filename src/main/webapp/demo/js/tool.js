;function UserInfo(){
};


UserInfo.clear = function(){
    plus.storage.removeItem('username');
    plus.storage.removeItem('password');
    plus.storage.removeItem('realName');
    plus.storage.removeItem('sysUserId');
}


UserInfo.auto_login = function(){
    var username = UserInfo.username();
    var pwd = UserInfo.password();
    var sysUserId = UserInfo.sysUserId();
    var realName = UserInfo.realName();
    if(!username || !pwd || !sysUserId || !realName){
        return false;
    }
    return true;
}


UserInfo.has_login = function(){
    var username = UserInfo.username();
    var pwd = UserInfo.password();
    var sysUserId = UserInfo.sysUserId();
    var realName = UserInfo.realName();
    if(!username || !pwd || !sysUserId || !realName){
        return false;
    }
    return true;
};

UserInfo.username = function(){
    if(arguments.length == 0){
        return plus.storage.getItem('username');
    }
    if(arguments[0] === ''){
        plus.storage.removeItem('username');
        return;
    }
    plus.storage.setItem('username', arguments[0]);
};
UserInfo.realName = function(){
    if(arguments.length == 0){
        return plus.storage.getItem('realName');
    }
    if(arguments[0] === ''){
        plus.storage.removeItem('realName');
        return;
    }
    plus.storage.setItem('realName', arguments[0]);
};

UserInfo.password = function(){
    if(arguments.length == 0){
        return plus.storage.getItem('password');
    }
    if(arguments[0] === ''){
        plus.storage.removeItem('password');
        return;
    }
    plus.storage.setItem('password', arguments[0]);
};
UserInfo.sysUserId = function(){
    if(arguments.length == 0){
        return plus.storage.getItem('sysUserId');
    }
    if(arguments[0] === ''){
        plus.storage.removeItem('sysUserId');
        return;
    }
    plus.storage.setItem('sysUserId', arguments[0]);
};