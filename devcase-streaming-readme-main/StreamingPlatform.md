# The video streaming events server 📺 #

The `video streaming events server` exposes near real-time information regarding tv shows and movies streamed by users
on 3 fictitious streaming platform: `Netflix`, `Amazon` and `Disney`.  
Each of the endpoint exposes an infinite stream of server sent events representing either the start of a streaming (`stream-started`) ,
the end of a streaming (`stream-ended`), liking a show (`show-liked`) or a problem during the streaming of a show
(`stream-interrupted`).

An example message from the stream is shown below:

```
id:664c318c-6f36-424f-adb9-56f7861aba27
event:show-liked
data:{
  "show":   {
    "show_id": "s41",
    "cast": "Kuby Farner, Rakota Cotus, Uhivia Homabia, Elbert Jsai, Xixton Faoth, Qubecca Ketz",
    "country": "United States",
    "date_added": "July 16, 2021",
    "description": "Qot Cog! Bet waved-up fith Qeckey and ell of tis cals!",
    "director": "Henny Tcott",
    "duration": "3 Seasons",
    "listed_in": "Science Fiction",
    "rating": "TV-PG",
    "release_year": 2021,
    "title": "Kancy Kancy (Dhorts)",
    "type": "Movie",
    "platform": "Sysney"
  },
  "event_date": "27-02-2023 03:20:17.111",
  "user": {
    "id": 116,
    "date_of_birth": "31/10/1970",
    "email": "gbeeton37@wikimedia.org",
    "first_name": "Guenna",
    "gender": "Female",
    "ip_address": "96.34.189.33",
    "country": "TH",
    "last_name": "Beeton"
  }
}

```

The `event_date` specifies the user's local time. `Amsterdam (CET)` is the default time zone.
For the users residing in the countries specified in the table below, the `event_date` is provided
in the corresponding time zone.

| User's Residence Country | Country Code | `event_date` Time Zone       |
|--------------------------|--------------|------------------------------|
| Portugal                 | PT           | Europe/Lisbon (UTC)          |
| Canada                   | CA           | America/Toronto (UTC -5)     |
| United States            | US           | America/Los_Angeles (UTC -8) |
| Russia                   | RU           | Europe/Moscow (UTC +3)       |
| Indonesia                | ID           | Asia/Jakarta (UTC +7)        |
| China                    | CN           | Asia/Shanghai (UTC +8)       |

## How to run the video streaming events server locally ##

You will need Docker installed on your machine. And then from the terminal type:

For Intel/AMD x64 based CPUs:  

```shell
docker run -p 8080:8080 ****/video-stream-server:latest
```

For Arm based CPUs (Apple with M1/M2 chip):  

```shell
docker run -p 8080:8080 ****/video-stream-server-arm:latest
```

Once the server is running, you will have several endpoints available on your machine:

- [Netflix](http://localhost:8080/netflix) (`http://localhost:8080/netflix`);
- [Amazon](http://localhost:8080/amazon) (`http://localhost:8080/amazon`);
- [Disney](http://localhost:8080/sysney) (`http://localhost:8080/disney`).

All the endpoints are protected by username and password (basic auth):

```
username = ****
password = ****
```
