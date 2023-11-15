function resolvedPromise() {
    return new Promise(function (resolve) {
        setTimeout(() => {
            resolve("Promise resolved after 500ms");
        }, 500);
    });
}

function rejectedPromise() {
    return new Promise(function (resolve, reject) {
        setTimeout(() => {
            reject("Promise rejected after 500ms");
        }, 500);
    });
}

// Call the resolvedPromise
resolvedPromise()
    .then(result => {
        console.log("Resolved Promise Result:", result);
    })
    .catch(error => {
        console.error("Resolved Promise Error:", error);
    });

// Call the rejectedPromise
rejectedPromise()
    .then(result => {
        console.log("Rejected Promise Result:", result); // This won't be executed
    })
    .catch(error => {
        console.error("Rejected Promise Error:", error);
    });