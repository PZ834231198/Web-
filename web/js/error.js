var i = 8;
function showNum(){
    i=i-1; document.getElementById("time").innerHTML=i;
    setTimeout('showNum()',1000);
}