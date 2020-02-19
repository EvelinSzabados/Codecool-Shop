let itemToCart=document.querySelectorAll(".toggle-button");
let cartPicture=document.querySelector("#modal-cart-image");

cartPicture.addEventListener("click",(event)=>{
    getData('/apiCart');
});

for(let item of itemToCart){
    item.addEventListener('click', (event) => {
        postData('/apiCart', {"id":item.id})
            .then((data) => {
                console.log(data); // JSON data parsed by `response.json()` call
            });
    });
}

// Example POST method implementation:
async function postData(url, data) {
    // Default options are marked with *
    const response = fetch(url, {
        method: 'POST', // *GET, POST, PUT, DELETE, etc.
        mode: 'cors', // no-cors, *cors, same-origin
        cache: 'no-cache', // *default, no-cache, reload, force-cache, only-if-cached
        credentials: 'same-origin', // include, *same-origin, omit
        headers: {
            'Content-Type': 'application/json'
            // 'Content-Type': 'application/x-www-form-urlencoded',
        },
        redirect: 'follow', // manual, *follow, error
        referrerPolicy: 'no-referrer', // no-referrer, *client
        body: JSON.stringify(data) // body data type must match "Content-Type" header
    });
    return response; // parses JSON response into native JavaScript objects
}

function getData(url) {
    fetch(url)  // set the path; the method is GET by default, but can be modified with a second parameter
        .then((response) => response.json())  // parse JSON format into JS object
        .then((data) => {
            console.log(data);
        });
}




