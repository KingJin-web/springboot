//import axios from 'axios';
//import popup from 'Popup';
// import axios from "./axios";

function getId() {
//名言类型：1——爱情 2——道德 3——青春 4——愿望 5——集体
// 6——理想 7——志向 8——人才 9——谦虚 10——人格 11——天才 12——青年
// 13——社会 14——国家 15——财富 16——智慧 17——修养 18——工作 19——妇女 20——儿童
// 21——思想 22——理智 23——学习 24——科学 25——信仰 26——诚信 27——读书 28——成败 29——奉献
// 30——劳动 31——节约 32——教育 33——企业 34——事业 35——时间 36——勤奋 37——民族 38——真理 39——友谊
// 40——自由 41——心理 42——心灵 43——人生 44——幸福 45——团结
// 返回参数说明：
    return randomNum(2, 45);
}
function getToken(){
    return "LwExDtUWhF3rH5ib";
}
function getmingyan() {
    axios.get('https://v2.alapi.cn/api/mingyan',
        {
            params: {
                typeid: getId(),
                token: "LwExDtUWhF3rH5ib"
            }
        }).then((response) => {
        console.log(response.data.data);
        if (response.data.code === 0) {
            return response.data.data;
        } else {
            return "";
        }
    }).catch((reject) => {
        console.log(reject)
    });
}


function randomNum(minNum, maxNum) {
    switch (arguments.length) {
        case 1:
            return parseInt(Math.random() * minNum + 1, 10);
            break;
        case 2:
            return parseInt(Math.random() * (maxNum - minNum + 1) + minNum, 10);
            break;
        default:
            return 0;
            break;
    }
}