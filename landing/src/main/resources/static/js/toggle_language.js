
function displayLanguage(elementName, display) {
    var elements = document.getElementsByClassName(elementName);
    for (var i = 0, length = elements.length; i < length; i++) {
        elements[i].style.display = display;
    }
}

function arabLanguage() {
    displayLanguage("kurdish", "none");
    displayLanguage("arab", "block");

    sessionStorage.setItem('languageValue', 'arab');
    document.getElementById("lang_ar").classList.add("hidden");
    document.getElementById("lang_ku").classList.remove("hidden");
	
}

function kurdishLanguage() {
    displayLanguage("arab", "none");
    displayLanguage("kurdish", "block");
    
    sessionStorage.setItem('languageValue', 'kurdish');
    document.getElementById("lang_ar").classList.remove("hidden");
    document.getElementById("lang_ku").classList.add("hidden");
	
}

var languageValue = sessionStorage.getItem('languageValue');
if (languageValue === null) {
    sessionStorage.setItem('languageValue', 'arab');
    arabLanguage();
} else {
    if (languageValue == 'kurdish') {
        kurdishLanguage();
    } else {
        arabLanguage();
    }
}


