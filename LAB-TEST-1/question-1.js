// Function to filter non-strings and convert remaining words to lowercase
function lowerCaseWords(mixedArray) {
    return new Promise((resolve, reject) => {
        const filteredAndLowercased = mixedArray.map(item => {
            if (typeof item === 'string') {
                return item.toLowerCase();
            }
            return null; // non-strings will be filtered out
        }).filter(item => item !== null);

        if (filteredAndLowercased.length > 0) {
            resolve(filteredAndLowercased);
        } else {
            reject("No valid strings found in the array");
        }
    });
}

// Example usage:
const mixedArray = ['PIZZA', 10, true, 25, false, 'Wings'];

lowerCaseWords(mixedArray)
    .then(result => {
        console.log(result);
    })
    .catch(error => {
        console.error("Error:", error);
    });
