function SlideShow(c) {
    var a = document.getElementById("slideContainer"), //最外面的div层
		f = document.getElementById("slidesImgs").getElementsByTagName("li"), //幻灯片的li
		h = document.getElementById("slideBar"), //序列号的div层
		n = h.getElementsByTagName("li"), //序列号的li
		d = f.length, //幻灯片个数
		c = c || 3000, //秒数
		e = lastI = 0, j, m;
    function b() {
        m = setInterval(function () {
            e = e + 1 >= d ? e + 1 - d : e + 1;//如果e比幻灯片个数小就跳下一个否则跳回第一个
            g();
        }, c);
    }
    function k() {
        clearInterval(m);//停止执行setInterval()
    }
    function g() {
		//上一个幻灯片跳下一个幻灯片，同时序号跟着变动 f幻灯片，n序号。
        f[lastI].style.display = "none";
        n[lastI].className = "";
        f[e].style.display = "block";
        n[e].className = "on";
        lastI = e;
    }
    f[e].style.display = "block";
    //鼠标移动到幻灯片上时停止自动跳幻灯片
	a.onmouseover = k;
	//鼠标移出去之后继续幻灯片自动跳
    a.onmouseout = b;
	//鼠标移动到序号上时候跳转相应幻灯片
    h.onmouseover = function (i) {
        j = i ? i.target : window.event.srcElement;
        if (j.nodeName === "LI") {
            e = parseInt(j.innerHTML, 10) - 1;
            g();
        }
    };
    b();
}
;