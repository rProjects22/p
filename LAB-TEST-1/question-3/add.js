const fs = require('fs');
const path = require('path');

const logsDirectory = path.join(__dirname, 'Logs');

// Function to create log files
function createLogFiles() {
    return new Promise((resolve, reject) => {
        // Create the Logs directory if it does not exist
        if (!fs.existsSync(logsDirectory)) {
            fs.mkdirSync(logsDirectory);
        }

        // Change the current process to the Logs directory
        process.chdir(logsDirectory);

       // Create 10 log files and write text into them
       for (let i = 1; i <= 10; i++) {
          const fileName = `log${i}.txt`;
          const fileContent = `This is log file ${i}`;
          fs.writeFileSync(fileName, fileContent);
          console.log(`Created file: ${fileName}`);
        }

        resolve();
    });
}

// Execute the function
createLogFiles();