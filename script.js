const API_URL = "http://localhost:8080/api/pets";
const ADOPTION_API_URL = "http://localhost:8080/api/adoptions";


// ===============================
// LOGIN
// ===============================

function showLogin() {
    alert("Login / Register feature will be available soon.");
}


// ===============================
// SELECT PET
// ===============================

function selectPet(petName) {

    localStorage.setItem("selectedPet", petName);

    window.location.href = "adoption.html";
}


// ===============================
// LOAD PETS FROM BACKEND
// ===============================

function loadPets() {

    const petContainer = document.getElementById("petContainer");

    if (!petContainer) {
        return;
    }

    fetch(API_URL)
        .then(response => response.json())
        .then(pets => {

            petContainer.innerHTML = "";

            pets.forEach(pet => {

                const petCard = document.createElement("div");

                petCard.className = "pet-card";

                petCard.innerHTML = `
                    <img src="${pet.imageUrl}" alt="${pet.name}">

                    <div class="pet-info">

                        <h3>${pet.name}</h3>

                        <p><strong>Breed:</strong> ${pet.breed}</p>

                        <p><strong>Age:</strong> ${pet.age} years</p>

                        <p><strong>Gender:</strong> ${pet.gender}</p>

                        <p><strong>Type:</strong> ${pet.type}</p>

                        <p><strong>Location:</strong> ${pet.location}</p>

                        <p>${pet.description}</p>

                        <button onclick="selectPet('${pet.name}')">
                            Adopt Now
                        </button>

                    </div>
                `;

                petContainer.appendChild(petCard);
            });
        })
        .catch(error => {

            console.error("Error loading pets:", error);

            petContainer.innerHTML =
                "<p>Unable to load pets. Please try again.</p>";
        });
}


// ===============================
// LOAD SELECTED PET
// ===============================

function loadSelectedPet() {

    const selectedPetInput = document.getElementById("selectedPet");

    if (!selectedPetInput) {
        return;
    }

    const selectedPet = localStorage.getItem("selectedPet");

    if (selectedPet) {
        selectedPetInput.value = selectedPet;
    }
}


// ===============================
// ADOPTION FORM SUBMIT
// ===============================

function setupAdoptionForm() {

    const adoptionForm = document.getElementById("adoptionForm");

    if (!adoptionForm) {
        return;
    }

    adoptionForm.addEventListener("submit", function(event) {

        event.preventDefault();

        const adoptionData = {

            adopterName: document.getElementById("adopterName").value,
            email: document.getElementById("email").value,
            phone: document.getElementById("phone").value,
            address: document.getElementById("address").value,
            selectedPet: document.getElementById("selectedPet").value,
            reason: document.getElementById("reason").value

        };


        fetch(ADOPTION_API_URL, {

            method: "POST",

            headers: {
                "Content-Type": "application/json"
            },

            body: JSON.stringify(adoptionData)

        })

        .then(response => {

            if (!response.ok) {
                throw new Error("Failed to submit application");
            }

            return response.json();

        })

        .then(data => {

            alert("Adoption application submitted successfully!");

            adoptionForm.reset();

            localStorage.removeItem("selectedPet");

        })

        .catch(error => {

            console.error("Error:", error);

            alert("Unable to submit application. Please try again.");

        });

    });
}


// ===============================
// PAGE LOAD
// ===============================

document.addEventListener("DOMContentLoaded", function() {

    loadPets();

    loadSelectedPet();

    setupAdoptionForm();

});