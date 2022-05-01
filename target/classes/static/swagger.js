

function showDiv() {
    const targetDiv = document.getElementById("afficher");
    if (targetDiv.style.display == "none"||targetDiv.style.display=="") {
        targetDiv.style.display = "block";
    } else {
        targetDiv.style.display = "none";
    }
}

function showDivGet() {
    const targetDiv = document.getElementById("afficherContentGet");
    console.log(targetDiv.style.display)
    if (targetDiv.style.display == "none"||targetDiv.style.display=="") {
        targetDiv.style.display = "block";
    } else {
        targetDiv.style.display = "none";
    }
}

function showDivDel() {
    const targetDiv = document.getElementById("afficherContentDel");
    if (targetDiv.style.display == "none"||targetDiv.style.display=="") {
        targetDiv.style.display = "block";
    } else {
        targetDiv.style.display = "none";
    }
}

function showDivPost() {
    const targetDiv = document.getElementById("afficherContentPost");
    if (targetDiv.style.display == "none"||targetDiv.style.display=="") {
        targetDiv.style.display = "block";
    } else {
        targetDiv.style.display = "none";
    }
}
