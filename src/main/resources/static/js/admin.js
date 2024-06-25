console.log("Admin Script is here")

document.querySelector("#image_file_input").addEventListener("change", function (event) {
    let file = event.target.files[0];
    let reader = new FileReader();
    reader.onload = function () {
        // document.getElementById("Upload_image_preview").src = reader.result;
        document.querySelector("#Upload_image_preview").setAttribute("src", reader.result)
    }
    reader.readAsDataURL(file);
})