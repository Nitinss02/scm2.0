console.log("Its is contact Page");
var viewmodule = document.getElementById("view_contact_model")

const baseURL = 'http://localhost:8080';
// options with default values
const options = {
    placement: 'bottom-right',
    backdrop: 'dynamic',
    backdropClasses:
        'bg-gray-900/50 dark:bg-gray-900/80 fixed inset-0 z-40',
    closable: true,
    onHide: () => {
        console.log('modal is hidden');
    },
    onShow: () => {
        console.log('modal is shown');
    },
    onToggle: () => {
        console.log('modal has been toggled');
    },
};

// instance options object
const instanceOptions = {
    id: 'view_contact_model',
    override: true
};

const contactModel = new Modal(viewmodule, options, instanceOptions)

function opencontactModule() {
    contactModel.show();
}
function closeContactModule() {
    contactModel.hide();
}

async function loadConatactData(id) {

    try {
        let data = await (await fetch(`${baseURL}/api/contact/${id}`)).json();
        console.log(data);
        document.querySelector("#profile_img").src = data.picture;
        document.querySelector("#contact_name").innerHTML = data.name;
        document.querySelector("#contact_email").innerHTML = data.email;
        document.querySelector("#Phone_number").innerHTML = data.phoneNumber;
        document.querySelector("#address").innerHTML = data.address;
        document.querySelector("#contact_decription").innerHTML = data.description;
        if (data.favourite == true) {
            contact_favourite.innerHTML = "<i class='fa-solid fa-heart text-red-700 text-3xl'></i>"
        }

        document.querySelector("#LinkedIn").innerHTML = data.linkedinlink;
        document.querySelector("#LinkedIn").href = data.linkedinlink;
        document.querySelector("#websiteLink").innerHTML = data.websiteLink;
        document.querySelector("#websiteLink").href = data.websiteLink;


        opencontactModule();
    } catch (error) {
        console.log(error);
    }
    // fetch(`http://localhost:8080/api/contact/${id}`)
    //     .then(async (response) => {
    //         let data = await response.json();
    //         console.log(data);
    //         return data;
    //     }).catch((error) => {
    //         console.log(error);
    //     });

    console.log(id);


}
// delete contact

async function DeleteContact(id) {
    Swal.fire({
        title: "Do you want to delete the contact?",
        icon: "warning",
        showCancelButton: true,
        confirmButtonText: "delete",

    }).then((result) => {
        /* Read more about isConfirmed, isDenied below */
        if (result.isConfirmed) {
            const url = `${baseURL}/user/contact/delete/${id}`;
            window.location.replace(url);
        }
    });
}