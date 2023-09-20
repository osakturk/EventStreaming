FROM mongo

COPY events.timezoneInfo.json /timezoneinfo.json

CMD mongoimport --host mongodb --db events --collection timezoneinfo --type json --file /timezoneInfo.json --jsonArray