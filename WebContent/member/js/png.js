// JavaScript Document
function correctPNG(){
var arVersion = navigator.appVersion.split("MSIE");
    var version = parseFloat(arVersion[1]);
    if ((version >= 5.5) && (version < 7) && (document.body.filters)) {
        for (var i = 0; i < document.images.length; i++) {
            var img = document.images[i];
            var LW = img.width;
            var LH = img.height;
            var imgName = img.src.toUpperCase();
            if (imgName.substring(imgName.length - 3, imgName.length) == "PNG")
            {
                img.style.filter += "progid:DXImageTransform.Microsoft.AlphaImageLoader(src=" + img.src + ", sizingmethod=scale);";
                img.src = "Images/png.gif";
                img.width = LW;
                img.height = LH;
            }
        }
    }
}
window.attachEvent("onload", correctPNG);