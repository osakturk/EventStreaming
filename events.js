db = db.getSiblingDB('events');
db.createUser(
    {
        user: 'root', 
        pwd: 'root', 
        roles: ['readWrite']
    }
);
