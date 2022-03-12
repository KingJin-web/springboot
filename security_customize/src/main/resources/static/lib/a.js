var e = Object.defineProperty, a = Object.defineProperties, s = Object.getOwnPropertyDescriptors,
    t = Object.getOwnPropertySymbols, o = Object.prototype.hasOwnProperty, c = Object.prototype.propertyIsEnumerable,
    r = (a, s, t) => s in a ? e(a, s, {enumerable: !0, configurable: !0, writable: !0, value: t}) : a[s] = t,
    n = (e, a) => {
        for (var s in a || (a = {})) o.call(a, s) && r(e, s, a[s]);
        if (t) for (var s of t(a)) c.call(a, s) && r(e, s, a[s]);
        return e
    }, i = (e, t) => a(e, s(t));
import {
    o as l,
    c as d,
    r as p,
    a as m,
    b as u,
    p as k,
    d as f,
    F as y,
    k as v,
    u as w,
    e as g,
    f as b,
    g as h,
    t as x,
    h as C,
    w as j,
    i as S,
    _ as P,
    j as _,
    l as K,
    m as A,
    n as W,
    q as I,
    s as L,
    v as O,
    x as V
} from "./vendor.e0b00493.js";

!function () {
    const e = document.createElement("link").relList;
    if (!(e && e.supports && e.supports("modulepreload"))) {
        for (const e of document.querySelectorAll('link[rel="modulepreload"]')) a(e);
        new MutationObserver((e => {
            for (const s of e) if ("childList" === s.type) for (const e of s.addedNodes) "LINK" === e.tagName && "modulepreload" === e.rel && a(e)
        })).observe(document, {childList: !0, subtree: !0})
    }

    function a(e) {
        if (e.ep) return;
        e.ep = !0;
        const a = function (e) {
            const a = {};
            return e.integrity && (a.integrity = e.integrity), e.referrerpolicy && (a.referrerPolicy = e.referrerpolicy), "use-credentials" === e.crossorigin ? a.credentials = "include" : "anonymous" === e.crossorigin ? a.credentials = "omit" : a.credentials = "same-origin", a
        }(e);
        fetch(e.href, a)
    }
}();
var N = (e, a) => {
    for (const [s, t] of a) e[s] = t;
    return e
};
const Q = {class: "NinjaLogo", src: "/assets/logo.03d6d6da.png", alt: "logo"};
const U = {
        components: {
            Logo: N({}, [["render", function (e, a) {
                return l(), d("img", Q)
            }]])
        }
    }, D = {class: "header"}, R = {class: "header-wrapper"}, z = {class: "flex items-center"},
    q = (e => (k("data-v-1f23ce5f"), e = e(), f(), e))((() => m("p", {class: "pl-3 select-none"}, "Ninja", -1)));
var T = N(U, [["render", function (e, a, s, t, o, c) {
    const r = p("Logo");
    return l(), d("div", D, [m("div", R, [m("div", z, [u(r, {class: "h-10 w-10"}), q])])])
}], ["__scopeId", "data-v-1f23ce5f"]]);
const E = {class: "main"}, J = {
    setup: e => (e, a) => {
        const s = p("router-view");
        return l(), d(y, null, [u(T), m("div", E, [u(s)])], 64)
    }
};
const $ = v.create({prefixUrl: "/api", retry: {limit: 0}});

function Z(e) {
    return $.post("WSCKLogin", {json: e}).json()
}

const B = {
        setup() {
            const e = w();
            g();
            let a = b({remark: "", jdwsck: void 0, nickName: void 0, timestamp: void 0});
            const s = async () => {
                try {
                    const e = localStorage.getItem("eid"), s = localStorage.getItem("wseid");
                    if (!e && !s) return void t();
                    if (e) {
                        const s = await function (e) {
                            const a = new URLSearchParams;
                            return a.set("eid", e), $.get("userinfo", {searchParams: a}).json()
                        }(e);
                        if (!s) return P.error("获取用户CK信息失败，请重重新登录"), void t();
                        a.nickName = s.data.nickName, a.timestamp = new Date(s.data.timestamp).toLocaleString()
                    }
                    if (s) {
                        const e = await getWSCKUserinfoAPI(s);
                        if (!e) return P.error("获取用户WSCK信息失败，请重重新登录"), void t();
                        a.nickName = e.data.nickName, a.timestamp = new Date(e.data.timestamp).toLocaleString()
                    }
                } catch (e) {
                    console.error(e)
                }
            };
            h(s);
            const t = () => {
                localStorage.removeItem("eid"), localStorage.removeItem("wseid"), e.push("/login")
            };
            return i(n({}, x(a)), {
                activity: [{
                    name: "玩一玩（可找到大多数活动）",
                    address: "京东 APP 首页-频道-边玩边赚",
                    href: "https://funearth.m.jd.com/babelDiy/Zeus/3BB1rymVZUo4XmicATEUSDUgHZND/index.html"
                }, {name: "宠汪汪", address: "京东APP-首页/玩一玩/我的-宠汪汪"}, {
                    name: "东东萌宠",
                    address: "京东APP-首页/玩一玩/我的-东东萌宠"
                }, {name: "东东农场", address: "京东APP-首页/玩一玩/我的-东东农场"}, {
                    name: "东东工厂",
                    address: "京东APP-首页/玩一玩/我的-东东工厂"
                }, {name: "东东超市", address: "京东APP-首页/玩一玩/我的-东东超市"}, {
                    name: "领现金",
                    address: "京东APP-首页/玩一玩/我的-领现金"
                }, {name: "东东健康社区", address: "京东APP-首页/玩一玩/我的-东东健康社区"}, {
                    name: "京喜农场",
                    address: "京喜APP-我的-京喜农场"
                }, {name: "京喜牧场", address: "京喜APP-我的-京喜牧场"}, {name: "京喜工厂", address: "京喜APP-我的-京喜工厂"}, {
                    name: "京喜财富岛",
                    address: "京喜APP-我的-京喜财富岛"
                }, {name: "京东极速版红包", address: "京东极速版APP-我的-红包"}], getInfo: s, logout: t, delAccount: async () => {
                    try {
                        const e = localStorage.getItem("eid"), a = await function (e) {
                            return $.post("delaccount", {json: e}).json()
                        }({eid: e});
                        200 !== a.code ? P.error(a.message) : (P.success(a.message), setTimeout((() => {
                            t()
                        }), 1e3))
                    } catch (e) {
                        console.error(e)
                    }
                }, changeremark: async () => {
                    try {
                        const s = localStorage.getItem("eid"), t = localStorage.getItem("wseid"), o = a.remark;
                        if (s) {
                            const e = await function (e) {
                                return $.post("update/remark", {json: e}).json()
                            }({eid: s, remark: o});
                            200 !== e.code ? P.success(e.message) : P.error(e.message)
                        }
                        if (t) {
                            const a = await (e = {wseid: t, remark: o}, $.post("updateWSCK/remark", {json: e}).json());
                            200 !== a.code ? P.success(a.message) : P.error(a.message)
                        }
                    } catch (s) {
                        console.error(s)
                    }
                    var e
                }, WSCKLogin: async () => {
                    try {
                        const e = a.jdwsck.match(/wskey=(.*?);/) && a.jdwsck.match(/wskey=(.*?);/)[1],
                            s = a.jdwsck.match(/pin=(.*?);/) && a.jdwsck.match(/pin=(.*?);/)[1];
                        if (e && s) {
                            const a = await Z({wskey: e, pin: s});
                            a.data.wseid ? (localStorage.setItem("wseid", a.data.wseid), P.success(a.message)) : P.error(a.message || "wskey 解析失败，请检查后重试！")
                        } else P.error("wskey 解析失败，请检查后重试！")
                    } catch (e) {
                        console.error(e)
                    }
                }, delWSCKAccount: async () => {
                    try {
                        const e = localStorage.getItem("wseid"), a = await function (e) {
                            return $.post("WSCKDelaccount", {json: e}).json()
                        }({wseid: e});
                        200 !== a.code ? P.error(a.message) : (P.success(a.message), setTimeout((() => {
                            t()
                        }), 1e3))
                    } catch (e) {
                        console.error(e)
                    }
                }, openUrlWithJD: e => {
                    const a = encodeURIComponent(`{"category":"jump","des":"m","action":"to","url":"${e}"}`);
                    window.location.href = `openapp.jdmobile://virtual?params=${a}`, console.log(window.location.href)
                }
            })
        }
    }, G = e => (k("data-v-67328e70"), e = e(), f(), e), H = {class: "content"}, F = {class: "card"},
    M = G((() => m("div", {class: "card-header"}, [m("p", {class: "card-title"}, "个人中心")], -1))),
    X = {class: "card-body"}, Y = {class: "card-footer"}, ee = A("退出登录"), ae = A("删除CK"), se = {class: "card"},
    te = _('<div class="card-header" data-v-67328e70><p class="card-title" data-v-67328e70>WSCK 录入</p><div class="card-body text-base leading-6" data-v-67328e70><b data-v-67328e70>wskey有效期长达一年，请联系管理员确认使用，慎重！</b><p data-v-67328e70>删WSCK在下方。</p><b data-v-67328e70>也可以保持pin不变，随意更改wskey，等同于删除WSCK。改密码解决一切CK泄露问题。</b><p data-v-67328e70>用户须手动提取pin和wskey，格式如：&quot;pin=xxxxxx;wskey=xxxxxxxxxx;&quot;。</p><p class="card-subtitle" data-v-67328e70>——IOS用户手机抓包APP <a style="" href="https://apps.apple.com/cn/app/stream/id1312141691" target="_blank" id="downiOSApp" data-v-67328e70>点击跳转安装</a></p><p class="card-subtitle" data-v-67328e70>——在api.m.jd.com域名下找POST请求大概率能找到wskey。</p><p class="card-subtitle" data-v-67328e70>wskey在录入后立马上线，系统会在指定时间检查wskey，有效则自动转换出cookie登录</p><p class="card-subtitle" data-v-67328e70>cookie失效后，也会在系统设定的指定时间内自动转换出新的cookie，实现一次录入长期有效</p><b data-v-67328e70>wskey会随着京东app的退出登录和更改密码而失效，清楚app数据或者卸载软件不会影响。</b></div></div>', 1),
    oe = {class: "card-body text-center"}, ce = {class: "card-footer"}, re = A("重新录入"), ne = A("删除WSCK"),
    ie = {class: "card"},
    le = G((() => m("div", {class: "card-header"}, [m("p", {class: "card-title"}, "修改备注（CK和WSCK同步）")], -1))),
    de = {class: "card-body text-center"}, pe = {class: "card-footer"}, me = A("修改"), ue = {class: "card"},
    ke = G((() => m("div", {class: "card-header"}, [m("p", {class: "card-title"}, "常见活动位置"), m("span", {class: "card-subtitle"}, "下面是一些常见活动的位置")], -1))),
    fe = {class: "card-body"}, ye = {class: "pr-2"}, ve = ["onClick"];
var we = N(B, [["render", function (e, a, s, t, o, c) {
    const r = p("el-button"), n = p("el-input");
    return l(), d("div", H, [m("div", F, [M, m("div", X, [m("p", null, "昵称：" + C(e.nickName), 1), m("p", null, "更新时间：" + C(e.timestamp), 1)]), m("div", Y, [u(r, {
        size: "small",
        auto: "",
        onClick: t.logout
    }, {default: j((() => [ee])), _: 1}, 8, ["onClick"]), u(r, {
        type: "danger",
        size: "small",
        auto: "",
        onClick: t.delAccount
    }, {
        default: j((() => [ae])),
        _: 1
    }, 8, ["onClick"])])]), m("div", se, [te, m("div", oe, [u(n, {
        modelValue: e.jdwsck,
        "onUpdate:modelValue": a[0] || (a[0] = a => e.jdwsck = a),
        placeholder: "pin=xxxxxx;wskey=xxxxxxxxxx;",
        size: "small",
        clearable: "",
        class: "my-4 w-full"
    }, null, 8, ["modelValue"])]), m("div", ce, [u(r, {
        type: "success",
        size: "small",
        auto: "",
        onClick: t.WSCKLogin
    }, {default: j((() => [re])), _: 1}, 8, ["onClick"]), u(r, {
        type: "danger",
        size: "small",
        auto: "",
        onClick: t.delWSCKAccount
    }, {
        default: j((() => [ne])),
        _: 1
    }, 8, ["onClick"])])]), m("div", ie, [le, m("div", de, [u(n, {
        modelValue: e.remark,
        "onUpdate:modelValue": a[1] || (a[1] = a => e.remark = a),
        size: "small",
        clearable: "",
        class: "my-4 w-full"
    }, null, 8, ["modelValue"])]), m("div", pe, [u(r, {
        type: "success",
        size: "small",
        auto: "",
        onClick: t.changeremark
    }, {
        default: j((() => [me])),
        _: 1
    }, 8, ["onClick"])])]), m("div", ue, [ke, m("div", fe, [m("ul", null, [(l(!0), d(y, null, S(t.activity, ((e, a) => (l(), d("li", {
        key: a,
        class: "leading-normal"
    }, [m("span", null, C(e.name) + "：", 1), m("span", ye, C(e.address), 1), e.href ? (l(), d("a", {
        key: 0,
        class: "text-blue-400",
        href: "#",
        onClick: a => t.openUrlWithJD(e.href)
    }, "直达链接", 8, ve)) : K("", !0)])))), 128))])])])])
}], ["__scopeId", "data-v-67328e70"]]);
const ge = {
        setup() {
            const e = w();
            g();
            let a = b({
                marginCount: 0,
                allowAdd: !0,
                cookie: "",
                QRCode: void 0,
                qrCodeVisibility: !1,
                token: void 0,
                okl_token: void 0,
                cookies: void 0,
                timer: void 0,
                waitLogin: !1,
                marginWSCKCount: 0,
                allowWSCKAdd: !0,
                jdwsck: void 0,
                showQR: !1,
                showWSCK: !1,
                showCK: !0
            });
            const s = async () => {
                try {
                    const e = (await $.get("info").json()).data;
                    a.marginCount = e.marginCount, a.allowAdd = e.allowAdd, a.marginWSCKCount = e.marginWSCKCount, a.allowWSCKAdd = e.allowWSCKAdd, a.showQR = e.showQR, a.showWSCK = e.showWSCK, a.showCK = e.showCK
                } catch (e) {
                    console.error(e)
                }
            }, t = async () => {
                if (this.showQR) try {
                    const e = await $.get("qrcode").json();
                    a.token = e.data.token, a.okl_token = e.data.okl_token, a.cookies = e.data.cookies, a.QRCode = e.data.QRCode, a.QRCode && (a.waitLogin = !0, clearInterval(a.timer), a.timer = setInterval(o, 3e3))
                } catch (e) {
                    console.error(e), P.error("生成二维码失败！请重试或放弃")
                } else P.warning("扫码已禁用请手动抓包")
            }, o = async () => {
                try {
                    const s = await function (e) {
                        return $.post("check", {json: e}).json()
                    }({token: a.token, okl_token: a.okl_token, cookies: a.cookies});
                    switch (null == s ? void 0 : s.data.errcode) {
                        case 0:
                            localStorage.setItem("eid", s.data.eid), P.success(s.message), clearInterval(a.timer), e.push("/");
                            break;
                        case 176:
                            break;
                        default:
                            P.error(s.message), a.waitLogin = !1, clearInterval(a.timer)
                    }
                } catch (s) {
                    clearInterval(a.timer), a.waitLogin = !1
                }
            };
            return h((() => {
                s(), t()
            })), i(n({}, x(a)), {
                getInfo: s, getQrcode: t, showQrcode: async () => {
                    a.qrCodeVisibility = !0
                }, ckeckLogin: o, jumpLogin: async () => {
                    const e = `openapp.jdmobile://virtual/ad?params={"category":"jump","des":"ThirdPartyLogin","action":"to","onekeylogin":"return","url":"https://plogin.m.jd.com/cgi-bin/m/tmauth?appid=300&client_type=m&token=${a.token}","authlogin_returnurl":"weixin://","browserlogin_fromurl":"${window.location.host}"}`;
                    window.location.href = e
                }, CKLogin: async () => {
                    try {
                        const s = a.cookie.match(/pt_key=(.*?);/) && a.cookie.match(/pt_key=(.*?);/)[1],
                            t = a.cookie.match(/pt_pin=(.*?);/) && a.cookie.match(/pt_pin=(.*?);/)[1];
                        if (s && t) {
                            const a = await function (e) {
                                return $.post("cklogin", {json: e}).json()
                            }({pt_key: s, pt_pin: t});
                            a.data.eid ? (localStorage.setItem("eid", a.data.eid), P.success(a.message), e.push("/")) : P.error(a.message || "cookie 解析失败，请检查后重试！")
                        } else P.error("cookie 解析失败，请检查后重试！")
                    } catch (s) {
                        console.error(s)
                    }
                }, WSCKLogin: async () => {
                    try {
                        const s = a.jdwsck.match(/wskey=(.*?);/) && a.jdwsck.match(/wskey=(.*?);/)[1],
                            t = a.jdwsck.match(/pin=(.*?);/) && a.jdwsck.match(/pin=(.*?);/)[1];
                        if (s && t) {
                            const a = await Z({wskey: s, pin: t});
                            a.data.wseid ? (localStorage.setItem("wseid", a.data.wseid), P.success(a.message), e.push("/")) : P.error(a.message || "wskey 解析失败，请检查后重试！")
                        } else P.error("wskey 解析失败，请检查后重试！")
                    } catch (s) {
                        console.error(s)
                    }
                }
            })
        }
    }, be = e => (k("data-v-91b1af62"), e = e(), f(), e), he = {class: "content"},
    xe = _('<div class="card" data-v-91b1af62><div class="card-header" data-v-91b1af62><div class="flex items-center justify-between" data-v-91b1af62><p class="card-title" data-v-91b1af62>Ninja提醒您</p></div></div><div class="card-body text-base leading-6" data-v-91b1af62><p data-v-91b1af62>为了您的财产安全请关闭免密支付以及打开支付验密（京东-设置-支付设置-支付验密设置）。</p><p data-v-91b1af62>建议京东账户绑定微信以保证提现能到账。</p><p data-v-91b1af62>由于京东异地登录限制，扫码获取cookie只有2小时有效期，因此暂时关闭扫码功能，现需手动抓取Cookie。</p><p data-v-91b1af62>且有效期不长，平均3-5天，因此需要及时更新。</p><b data-v-91b1af62>安全起见，WSCK可以在CK登录后录入，期限半永久。</b></div><div class="card-footet" data-v-91b1af62></div></div> <p>详情请点击： <a href="https://blog.csdn.net/qq_44737094/article/details/122283489;" rel="nofollow" target="_blank">这篇文章末尾查看详细获取cookie教程</a>', 1),
    Ce = {key: 0, class: "card"}, je = {class: "card-header"}, Se = {class: "flex items-center justify-between"},
    Pe = be((() => m("p", {class: "card-title"}, "扫码登录", -1))),
    _e = {class: "ml-2 px-2 py-1 bg-gray-200 rounded-full font-normal text-xs"},
    Ke = be((() => m("span", {class: "card-subtitle"}, " 请点击下方按钮登录，点击按钮后回到本网站查看是否登录成功，京东的升级提示不用管。 ", -1))),
    Ae = {class: "card-body text-center"}, We = {key: 0, class: "flex flex-col w-48 m-auto mt-4"}, Ie = A("扫描二维码登录"),
    Le = A("跳转到京东 App 登录"), Oe = ["src"], Ve = be((() => m("div", {class: "card-footer"}, null, -1))),
    Ne = {key: 1, class: "card"}, Qe = {class: "card-header"}, Ue = {class: "flex items-center justify-between"},
    De = be((() => m("p", {class: "card-title"}, "WSCK 录入", -1))),
    Re = {class: "ml-2 px-2 py-1 bg-gray-200 rounded-full font-normal text-xs"},
    ze = _('<div class="card-body text-base leading-6" data-v-91b1af62><b data-v-91b1af62>wskey有效期长达一年，请联系管理员确认使用（删不掉，慎用）</b><p data-v-91b1af62>用户须手动提取pin和wskey，格式如：&quot;pt_pin=xxxxxx;wskey=xxxxxxxxxx;&quot;。</p><p class="card-subtitle" data-v-91b1af62>——IOS用户手机抓包APP <a style="" href="https://apps.apple.com/cn/app/stream/id1312141691" target="_blank" id="downiOSApp" data-v-91b1af62>点击跳转安装</a></p><p class="card-subtitle" data-v-91b1af62>——在api.m.jd.com域名下找POST请求大概率能找到wskey。</p><p class="card-subtitle" data-v-91b1af62>wskey在录入后立马上线，系统会在指定时间检查wskey，有效则自动转换出cookie登录</p><p class="card-subtitle" data-v-91b1af62>cookie失效后，也会在系统设定的指定时间内自动转换出新的cookie，实现一次录入长期有效</p><b data-v-91b1af62>wskey会随着京东app的退出登录和更改密码而失效，清楚app数据或者卸载软件不会影响。</b></div><span class="card-subtitle" data-v-91b1af62> 请在下方输入您的 WSCK </span>', 2),
    qe = {class: "card-body text-center"}, Te = A("录入"), Ee = be((() => m("div", {class: "card-footet"}, null, -1))),
    Je = {key: 2, class: "card"}, $e = {class: "card-header"}, Ze = {class: "flex items-center justify-between"},
    Be = be((() => m("p", {class: "card-title"}, "CK 登录", -1))),
    Ge = {class: "ml-2 px-2 py-1 bg-gray-200 rounded-full font-normal text-xs"},
    He = be((() => m("div", {class: "card-body text-base leading-6"}, [m("p", null, [A("PC用户建议使用开源工具"), m("a", {
        style: {},
        href: "https://github.com/Waikkii/JD_Get_Cookie",
        target: "_blank",
        id: "waikiki"
    }, "JD_Get_Cookie"), A("获取cookie并在下方填写。")]), m("p", null, [A("手机用户可以使用Alook浏览器登录"), m("a", {
        style: {},
        href: "https://m.jd.com/",
        target: "_blank",
        id: "jd"
    }, "JD官网"), A("，并在菜单-工具箱-开发者工具-Cookies中获取（Android和iPhone通用）。")]), m("p", null, "另外也可以使用抓包工具（iPhone：Stream，Android：HttpCanary）抓取京东app的ck，要注意pt_key和pt_pin字段是以app_open开头的。"), m("p", null, "cookie直接填入输入框即可，Ninja会自动正则提取pt_key和pt_pin。")], -1))),
    Fe = be((() => m("span", {class: "card-subtitle"}, " 请在下方输入您的 cookie 登录。 ", -1))),
    Me = {class: "card-body text-center"}, Xe = A("登录"), Ye = be((() => m("div", {class: "card-footet"}, null, -1)));
const ea = [{path: "/", component: we}, {
    path: "/login", component: N(ge, [["render", function (e, a, s, t, o, c) {
        const r = p("el-button"), n = p("el-input");
        return l(), d("div", he, [xe, e.showQR ? (l(), d("div", Ce, [m("div", je, [m("div", Se, [Pe, m("span", _e, "余量：" + C(e.marginCount), 1)]), Ke]), m("div", Ae, [e.qrCodeVisibility ? (l(), d("img", {
            key: 1,
            src: e.QRCode,
            width: 256,
            class: "m-auto"
        }, null, 8, Oe)) : (l(), d("div", We, [u(r, {
            type: "primary",
            round: "",
            onClick: t.showQrcode
        }, {default: j((() => [Ie])), _: 1}, 8, ["onClick"]), u(r, {
            class: "mt-4 ml-0",
            type: "primary",
            round: "",
            onClick: t.jumpLogin
        }, {
            default: j((() => [Le])),
            _: 1
        }, 8, ["onClick"])]))]), Ve])) : K("", !0), e.showWSCK ? (l(), d("div", Ne, [m("div", Qe, [m("div", Ue, [De, m("span", Re, "余量：" + C(e.marginWSCKCount), 1)]), ze]), m("div", qe, [u(n, {
            modelValue: e.jdwsck,
            "onUpdate:modelValue": a[0] || (a[0] = a => e.jdwsck = a),
            placeholder: "pin=xxxxxx;wskey=xxxxxxxxxx;",
            size: "small",
            clearable: "",
            class: "my-4 w-full"
        }, null, 8, ["modelValue"]), u(r, {
            type: "primary",
            size: "small",
            round: "",
            onClick: t.WSCKLogin
        }, {
            default: j((() => [Te])),
            _: 1
        }, 8, ["onClick"])]), Ee])) : K("", !0), e.showCK ? (l(), d("div", Je, [m("div", $e, [m("div", Ze, [Be, m("span", Ge, "余量：" + C(e.marginCount), 1)]), He, Fe]), m("div", Me, [u(n, {
            modelValue: e.cookie,
            "onUpdate:modelValue": a[1] || (a[1] = a => e.cookie = a),
            size: "small",
            clearable: "",
            class: "my-4 w-full"
        }, null, 8, ["modelValue"]), u(r, {
            type: "primary",
            size: "small",
            round: "",
            onClick: t.CKLogin
        }, {default: j((() => [Xe])), _: 1}, 8, ["onClick"])]), Ye])) : K("", !0)])
    }], ["__scopeId", "data-v-91b1af62"]])
}], aa = W({history: I(), routes: ea}), sa = [O, V, P], ta = [P], oa = L(J);
sa.forEach((e => {
    oa.component(e.name, e)
})), ta.forEach((e => {
    oa.use(e)
})), oa.use(aa), oa.mount("#app");
