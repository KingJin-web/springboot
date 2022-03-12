// document.write("<script type='text/javascript' src='dist/mDialogMin.js'></script>");
// document.write('<link type="text/css" href="dist/dialog.css" rel="stylesheet">')
// document.write('<link rel="stylesheet" href="../layui/css/layui.css"  media="all">');
//


function changeShare_() {
    layer.open({
        type: 2
        , title: '添加用户'
        , content: 'usershare.html'
        , maxmin: true
        , area: ['500px', '450px']
        , btn: ['确定', '取消']
        , yes: function (index, layero) {
            var iframeWindow = window['layui-layer-iframe' + index],
                submitID = 'LAY-user-front-submit',
                submit = layero.find('iframe').contents().find('#' + submitID);
            //监听提交
            iframeWindow.layui.form.on('submit(' + submitID + ')', function (data) {
                var field = data.field; //获取提交的字段

                //提交 Ajax 成功后，静态更新表格中的数据
                $.ajax({});
                table.reload('LAY-user-front-submit'); //数据刷新
                layer.close(index); //关闭弹层
            });

            submit.trigger('click');
        }
    });
}


function alertMy(text) {
    if (text.includes("成功")) {
        layer.alert(text, {
            icon: 1,
            skin: 'layer-ext-moon'
        })
    } else {
        layer.alert(text, {
            icon: 3,
            skin: 'layer-ext-moon'
        })
    }
}

function alertDelete() {
    layer.confirm('您真的要删除这首歌曲吗？', {
        icon: 0,
        skin: 'layui-layer-lan',
        btn: ['确认', '取消'] //按钮
    }, function () {
        alert("删除成功");
    }, function () {
        layer.close(index)
    });
}


/**
 * 如果没有登录
 * 请先登录，跳转登录界面
 * 3秒后自动跳转
 */
function gotoLogin() {
    layer.confirm("请先登录 !", {
        icon: 2,
        shade: 1,
        btn: ['确认'], //按钮
        // window.location.href = "login.html";
    }, function () {
        window.location.href = "login.html";
    })


}

/**
 * 如果没有登录
 * 请先登录，跳转登录界面
 * 3秒后自动跳转
 */
function gotoLogin(text,path) {
    layer.confirm(text, {
        icon: 2,
        shade: 1,
        btn: ['确认'], //按钮
        // window.location.href = "login.html";
    }, function () {
        window.location.href = path;
    })


}


/**
 * //https://layer.layui.com/
 * https://layer.layui.com/test/more.html
 * https://www.layui.com/alone.html
 * https://layer.layui.com/hello.html
 * @param text 提示内容
 * @param path 跳转页面路径
 * @param success 成功的提示
 */
function alertLayer(text, path) {
    console.log(text)
    if (text.includes("成功")) {
        layer.confirm(text, {
            icon: 1,
            btn: ['确认'] //按钮
        }, function () {
            window.location.href = path;
        })

    } else {
        layer.alert(text, {
            icon: 3,
            skin: 'layer-ext-moon'
        })
    }
}

/**
 * 保存用户信息 提示弹窗 以及延迟加载
 * @param data 后台回调的信息
 */
function alertSave(data) {
    this.data = data;
    layer.load(0, {shade: false, time: 2000});
    setTimeout(" var text = data;alertSave2(text)", 1000);
}

function alertSave2(text) {

    if (text.includes("成功")) {
        layer.alert(text, {
            icon: 1,
            skin: 'layer-ext-moon'
        })
    } else {
        layer.alert(text, {
            icon: 3,
            skin: 'layer-ext-moon'
        })
    }
}

function returnLogin() {
    var url = "user.do?op=returnLogin"
    axios.get(url).then(ret => {
        alertMy(ret.data);
        window.location.href = "login.html";
    });
}





function alertAdv4() {
    layer.open({
        type: 1
        ,
        title: false //不显示标题栏
        ,
        closeBtn: false
        ,
        area: '300px;'
        ,
        shade: 0.8
        ,

        resize: false
        ,
        btn: ['火速围观', '残忍拒绝']
        ,
        btnAlign: 'c'
        ,
        moveType: 1 //拖拽模式，0或者1
        ,
        content: '\t\t<h1>安卓,苹果,Mac,Windows 好用的加速器</h1>\n' +
            '\t\t<h3>刷INS、访推特，完美支持高清1080P视频，无任何流量限制,真正免费的加速器</h3>\n' +
            '\t\t<a href="https://invited.antss002.com/aff/uXAP">立即前往</a>', //捕获的元素，注意：最好该指定的元素要存放在body最外层，否则可能被其它的相对元素所影响
    });
}

function alertMyVip() {
    console.log("====================\n===== 这是广告 会员看不到的======================\n=======");

    var i = Math.floor(Math.random() * 4);
    if (i == 1) {
        alertAdv1();
    } else if (i == 2) {
        alertAdv2();
    } else if (i == 3) {
        alertAdv3();
    } else {
        alertAdv4();
    }


}

function alertAdv3() {

    layer.open({
        type: 1
        ,
        title: false //不显示标题栏
        ,
        closeBtn: false
        ,
        area: '300px;'
        ,
        shade: 0.8
        ,

        resize: false
        ,
        btn: ['火速围观', '残忍拒绝']
        ,
        btnAlign: 'c'
        ,
        moveType: 1 //拖拽模式，0或者1
        ,
        content: '<h1>PayPal</h1>\n' +
            '\t\t<h3>一个账户，收款全球。0费用开户，享卖家保障，赢逾2亿用户。</h3>\n' +
            '\t\t<p>PayPal</p>',
        success: function (layero) {
            var btn = layero.find('.layui-layer-btn');
            btn.find('.layui-layer-btn0').attr({
                href: 'https://www.paypal.com/c2/webapps/mpp/account-selection?locale.x=zh_c2&pid=267417219&dclid=&gclid=EAIaIQobChMI8OG2xKiE7gIVCB68Ch3_OwbiEAEYASAAEgIZWfD_BwE'
                , target: '_blank'
            });
        }
    });
}

//左上角弹广告
function alertAdv2() {

    layer.open({
        type: 1
        ,
        title: false //不显示标题栏
        ,
        closeBtn: false
        ,
        area: '300px;'
        ,
        shade: 0.8
        ,

        resize: false
        ,
        btn: ['火速围观', '残忍拒绝']
        ,
        btnAlign: 'c'
        ,
        moveType: 1 //拖拽模式，0或者1
        ,
        content: '<h1>会员超值特惠 </h1> <p>三个月会员只需要20元</p><br>'
        ,
        success: function (layero) {
            var btn = layero.find('.layui-layer-btn');
            btn.find('.layui-layer-btn0').attr({
                href: 'Recharge.html'
                , target: '_blank'
            });
        }
    });
}

/**
 * 弹出式广告
 */
function alertAdv1() {
    layer.open({
        type: 1
        ,
        title: false //不显示标题栏
        ,
        closeBtn: false
        ,
        area: '300px;'
        ,
        shade: 0.8
        ,

        resize: false
        ,
        btn: ['火速围观', '残忍拒绝']
        ,
        btnAlign: 'c'
        ,
        moveType: 1 //拖拽模式，0或者1
        ,
        content: '<div style="padding: 50px; line-height: 22px; background-color: #393D49; color: #fff; font-weight: 300;">' +
            '<h2>云服务器ECS </h2><br>云服务器12月钜惠，新用户低至0.55折， 1核2G轻量服务器首年96元（可优惠续费3次），助您轻松上云</div>'
        ,
        success: function (layero) {
            var btn = layero.find('.layui-layer-btn');
            btn.find('.layui-layer-btn0').attr({
                href: 'https://www.aliyun.com/1111/new?userCode=t6jlywqr'
                , target: '_blank'
            });
        }
    });

}


//
// function myAlert(text) {
//     Dialog.init('<p style=" margin:8px 0;width:100%;padding:11px 8px;font-size:15px; solid #999;">' + text + '</p>', {
//         title: '提示',
//         button: {
//             确定: function () {
//                 Dialog.close(this);
//
//             },
//             取消: function () {
//                 Dialog.close(this);
//             }
//         },
//         time: 3000,
//     });
// }

// function myAlert(text, path, success) {
//     Dialog.init('<p style=" margin:8px 0;width:100%;padding:11px 8px;font-size:15px; solid #999;">' + text + '</p>', {
//         title: '提示',
//         button: {
//             确定: function () {
//                 if (text === success) {
//                     window.location.href = path;
//                 }
//                 Dialog.close(this);
//             },
//             取消: function () {
//                 Dialog.close(this);
//             }
//         },
//     });
// }