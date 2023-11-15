const fs = require('fs');
const path = require('path');

const logsDirectory = path.join(__dirname, 'Logs');

// Function to remove log files and the Logs directory
function removeLogFiles() {
    return new Promise((resolve, reject) => {
        // Check if the Logs directory exists
        fs.stat(logsDirectory, (err, stats) => {
            if (err || !stats.isDirectory()) {
                console.log("Logs directory does not exist.");
                resolve();
                return;
            }

            // Read the contents of the Logs directory
            fs.readdir(logsDirectory, (err, files) => {
                if (err) {
                    reject(err);
                    return;
                }

                // Output file names to delete
                console.log("Files to delete:");
                files.forEach(file => {
                    console.log(file);
                    // Remove each file
                    fs.unlinkSync(path.join(logsDirectory, file));
                });

                // Remove the Logs directory
                fs.rmdirSync(logsDirectory);

                console.log("Logs directory and files removed.");
                resolve();
            });
        });
    });
}

// Execute the function
removeLogFiles()