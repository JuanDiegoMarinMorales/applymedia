var timeleft = 60;
var downloadTimer = setInterval(function () {
  if (timeleft <= 0) {
    clearInterval(downloadTimer);
    document.getElementById("countdown").innerHTML = "0";
    document.getElementById("countdown2").innerHTML = "0";
    document.getElementById("btn").style = "display: block;"
  } else {
    document.getElementById("countdown").innerHTML = timeleft;
    document.getElementById("countdown2").innerHTML = timeleft;
  }
  timeleft -= 1;
}, 1000);
