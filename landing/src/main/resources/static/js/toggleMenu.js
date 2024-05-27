// Toogle button menu

const toggleButton = document.querySelector(".toggle");
const navigation = document.querySelector(".navigation");
const logo = document.querySelector(".logo")

toggleButton.addEventListener("click", () => {
  toggleButton.classList.toggle("active");
  navigation.classList.toggle("active");
  logo.classList.toggle("active");
});

navigation.addEventListener("click", () => {
  toggleButton.classList.toggle("active");
  navigation.classList.toggle("active");
  logo.classList.toggle("active");
});

// change pages
function changePage(whatis){
  hiddenPages()
  document.getElementById(whatis).classList.remove("hidden")
  document.getElementById("logoImage").src="/images/logo-dark.jpg";
  document.getElementById("toggle").classList.add("toggle-dark");
  document.getElementById("toggle").classList.remove("toggle-white");
  
  
}

function changePageHome(whatis){
  hiddenPages()
  document.getElementById(whatis).classList.remove("hidden")
  document.getElementById("logoImage").src="/images/logo.png";
  document.getElementById("toggle").classList.remove("toggle-dark");
  document.getElementById("toggle").classList.add("toggle-white")
  
}


function hiddenPages(){
  let pages = document.getElementsByClassName("page")

  for (const page of pages) {
      page.classList.add("hidden")
  }
}