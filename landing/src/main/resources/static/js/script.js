

// Get the modal
var modalTerms = document.getElementById("myTerms");

// Get the button that opens the modal
var btnTerms = document.getElementById("Terms");

// Get the <span> element that closes the modal
var spanTerms = document.getElementsByClassName("closeTerms")[0];

// When the user clicks the button, open the modal 
btnTerms.onclick = function() {
  modalTerms.style.display = "block";
}

// When the user clicks on <span> (x), close the modal
spanTerms.onclick = function() {
  modalTerms.style.display = "none";
}

// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
  if (event.target == modalTerms) {
    modalTerms.style.display = "none";
  }
}

// Get the modal
var modalPolicy = document.getElementById("myPolicy");

// Get the button that opens the modal
var btnPolicy = document.getElementById("Policy");

// Get the <span> element that closes the modal
var spanPolicy = document.getElementsByClassName("closePolicy")[0];

// When the user clicks the button, open the modal 
btnPolicy.onclick = function() {
  modalPolicy.style.display = "block";
}

// When the user clicks on <span> (x), close the modal
spanPolicy.onclick = function() {
  modalPolicy.style.display = "none";
}

// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
  if (event.target == modalPolicy) {
    modalPolicy.style.display = "none";
  }
}