let modal = document.getElementById('myModal');
let modalImg = document.getElementById("img01");
let captionText = document.getElementById("caption");
$("img#FirstProductImage").on("click", function () {
    modal.style.display = "block";
    modalImg.src = this.src;
    captionText.innerHTML = this.alt;
});

modal.onclick = function () {
    modal.style.display = "none";
}