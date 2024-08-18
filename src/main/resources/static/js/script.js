console.log("script loaded");


let currentTheme = getTheme();

document.addEventListener("DOMContentLoaded", () => {
    changeTheme();
})


function changeTheme() {
    document.querySelector("html").classList.add(currentTheme);

    //change Theme button
    changeThemePage(currentTheme, currentTheme);
    const changeThemeBtn = document.querySelector("#theme_change_btn")
    // changeThemeBtn.querySelector("span").textContent = currentTheme = "light" ? "dark" : "Light";
    changeThemeBtn.addEventListener("click", (e) => {
        console.log("Button clicked");
        let oldTheme = currentTheme;

        if (currentTheme === "dark") {
            currentTheme = "light";
        }
        else {
            currentTheme = "dark";
        }

        //updated to localStorage

        changeThemePage(currentTheme, oldTheme);
    })
}

function setTheme(Theme) {
    localStorage.setItem("Theme", Theme);
}


function getTheme() {
    let Theme = localStorage.getItem("Theme");
    return Theme ? Theme : "light";
}

function changeThemePage(Theme, oldTheme) {

    setTheme(currentTheme);
    document.querySelector("html").classList.remove(oldTheme);
    document.querySelector("html").classList.add(Theme);
    var thembtn = document.querySelector("#theme_change_btn");

    thembtn.querySelector("span").textContent = Theme == "light" ? "dark" : "light";
    // thembtn.querySelector("i").className == "fa-regular fa-lightbulb" ? "fa-regular fa-lightbulb" : "fa-solid fa-moon"


}