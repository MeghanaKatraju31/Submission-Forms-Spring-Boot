<!DOCTYPE html>
<html>
<head>
    <title>Form</title>
    <h1>WELCOME TO MY PAGE</h1>
    <h2>PLEASE ENTER YOUR DETAILS HERE</h2>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.1/moment.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/moment-timezone/0.5.34/moment-timezone-with-data.min.js"></script>
    <script>
        // Define a mapping of countries to their states and timezones
        const countryToStates = {
            "USA": {
                "California": "America/Los_Angeles",
                "Texas": "America/Chicago",
                "New York": "America/New_York",
                "Florida": "America/New_York",
                "Illinois": "America/Chicago",
                "Pennsylvania": "America/New_York",
                "Ohio": "America/New_York"
            },
            "India": {
                "Maharashtra": "Asia/Kolkata",
                "Karnataka": "Asia/Kolkata",
                "Tamil Nadu": "Asia/Kolkata",
                "Delhi": "Asia/Kolkata",
                "West Bengal": "Asia/Kolkata",
                "Gujarat": "Asia/Kolkata",
                "Rajasthan": "Asia/Kolkata"
            },
            "Australia": {
                "New South Wales": "Australia/Sydney",
                "Victoria": "Australia/Melbourne",
                "Queensland": "Australia/Brisbane",
                "Western Australia": "Australia/Perth",
                "South Australia": "Australia/Adelaide",
                "Tasmania": "Australia/Hobart",
                "Northern Territory": "Australia/Darwin"
            },
            "Canada": {
                "Ontario": "America/Toronto",
                "Quebec": "America/Montreal",
                "British Columbia": "America/Vancouver",
                "Alberta": "America/Edmonton",
                "Manitoba": "America/Winnipeg",
                "Saskatchewan": "America/Regina",
                "Nova Scotia": "America/Halifax"
            },
            "Germany": {
                "Bavaria": "Europe/Berlin",
                "Berlin": "Europe/Berlin",
                "North Rhine-Westphalia": "Europe/Berlin",
                "Hesse": "Europe/Berlin",
                "Saxony": "Europe/Berlin",
                "Baden-Württemberg": "Europe/Berlin",
                "Lower Saxony": "Europe/Berlin"
            }
        };

        // Define a mapping of IANA timezones to their common abbreviations
        const timezoneAbbreviations = {
            "America/Los_Angeles": "PST",
            "America/Chicago": "CST",
            "America/New_York": "EST",
            "Asia/Kolkata": "IST",
            "Australia/Sydney": "AEST",
            "Australia/Melbourne": "AEST",
            "Australia/Brisbane": "AEST",
            "Australia/Perth": "AWST",
            "Australia/Adelaide": "ACST",
            "Australia/Hobart": "AEST",
            "Australia/Darwin": "ACST",
            "America/Toronto": "EST",
            "America/Montreal": "EST",
            "America/Vancouver": "PST",
            "America/Edmonton": "MST",
            "America/Winnipeg": "CST",
            "America/Regina": "CST",
            "America/Halifax": "AST",
            "Europe/Berlin": "CET"
        };

        function updateStates() {
            const countrySelect = document.getElementById("country");
            const stateSelect = document.getElementById("state");
            const selectedCountry = countrySelect.value;
            const states = countryToStates[selectedCountry];

            // Clear previous states
            stateSelect.innerHTML = '<option value="">Select a state</option>';

            if (states) {
                // Populate states dropdown
                for (const state in states) {
                    const option = document.createElement("option");
                    option.value = state;
                    option.textContent = state;
                    stateSelect.appendChild(option);
                }
            }
        }

        function updateTimezone() {
            const countrySelect = document.getElementById("country");
            const stateSelect = document.getElementById("state");
            const selectedCountry = countrySelect.value;
            const selectedState = stateSelect.value;
            const timezoneField = document.getElementById("timezone");
            const timeField = document.getElementById("time");
            const ampmField = document.getElementById("ampm");

            const timezone = countryToStates[selectedCountry] ? countryToStates[selectedCountry][selectedState] : "Unknown";
            const timezoneAbbreviation = timezoneAbbreviations[timezone] || "Unknown";
            timezoneField.value = timezoneAbbreviation;

            if (timezone !== "Unknown") {
                // Get the current time in the selected timezone
                const currentTime = moment().tz(timezone);
                const hours = currentTime.hours();
                const minutes = currentTime.minutes();

                // Format time in 12-hour format with AM/PM
                const formattedTime = currentTime.format('hh:mm');
                timeField.value = formattedTime;
                ampmField.value = hours >= 12 ? "PM" : "AM";
            } else {
                timeField.value = '';
                ampmField.value = '';
            }
        }
    </script>
</head>
<body>
    <form action="/details" method="post">
        <label for="cid">CID:</label>
        <input type="text" id="cid" name="cid" required><br>

        <label for="cname">CName:</label>
        <input type="text" id="cname" name="cname" required><br>

        <label for="cemail">CEmail:</label>
        <input type="email" id="cemail" name="cemail" required><br>

        <label for="dateOfBirth">Date of Birth:</label>
        <input type="date" id="dateOfBirth" name="dateOfBirth" required><br>

        <label for="country">Country:</label>
        <select id="country" name="country" onchange="updateStates(); updateTimezone();" required>
            <option value="">Select a country</option>
            <option value="USA">USA</option>
            <option value="India">India</option>
            <option value="Australia">Australia</option>
            <option value="Canada">Canada</option>
            <option value="Germany">Germany</option>
        </select><br>

        <label for="state">State:</label>
        <select id="state" name="state" onchange="updateTimezone();" required>
            <option value="">Select a state</option>
            <!-- States will be populated based on the selected country -->
        </select><br>

        <label for="timezone">Timezone:</label>
        <input type="text" id="timezone" name="timezone" readonly><br>

        <label for="time">Time:</label>
        <input type="text" id="time" name="time" required readonly><br>

        <label for="ampm">AM/PM:</label>
        <input type="text" id="ampm" name="ampm" required readonly><br>

        <input type="submit" value="Submit">
    </form>
</body>
</html>

