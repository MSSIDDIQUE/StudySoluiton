const functions = require('firebase-functions');
const admin = require('firebase-admin');

admin.initializeApp({
    credential: admin.credential.cert({
      "type": "service_account",
      "project_id": "coachingapp-af070",
      "private_key_id": "98ee996fa827431f242fad399e84ec2158b58459",
      "private_key": "-----BEGIN PRIVATE KEY-----\nMIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCmuaeiq8zgldAQ\nzPOL/F4REx2F61kRBFMp7ZWby1LmW9x6fI3oR3mFb95rCGBGhJbvW0Sw6K3aQYw3\nohmmTOIu8kGtvZegaW/FXroZXN4w3IzRGzfIMIOWOHeA0JrO4VTNj7QSj/5HVWRc\nWqTAsc6eisaIL9k357Big+dmj3g2Jheeazeko8RDWRxNU9L1Ota2AX8FPqeeynIo\ny4HqU3us6czXqHWM+HHrLyvQSyfoi2XXTWvUK/H4zanvHACwTp8FOwFxgiVGQX5S\nouHITAXZ1j4st1BmJLhlEc45XSRi8sXnuv4g7to9/LuF7g2/w2sI3LTw2yXjnYYv\nIFwm9mzJAgMBAAECggEALtLGGx/ftpUsbYN5xK84Jdh1+qXZijdqOcTBNxNXsV1M\nItQ3MGxeWxTLZMjh7PwQvaEmvf5i0QCGphsu0+8UuLWNr4MnAcSKwOX1Iulbd2KB\nY7qepGjHk9lA6prrVz2eSOraWjo5YgQ/3tYQHhPxoMH9tsKlYRd/CHPd+YZCbwY4\nlCySaikLdO29vcivSJflS5UeX0wl16QzFWrb8FvXJ8iIn3JN9NV+h5jR13zcvWz3\nO7ch60wft8UVg9vxTAqRxNeU0dJ5gNz6qbf6QwPgKRkgZFjFFpXV3CgRMuAKfPUS\nRBA183Phin8oTkHS12LQlgkkLSLbamWPZpd8a9FJNwKBgQDU+kuh4D5dBKa8Ot2J\nYlFzSaJbr9XsGR9z8WQrHKoN9CLnhk/8nXjfFzawVCXEkONbuul9W4RyNLUsDzkH\nW3DwtAFn5FeRP2iEZRK10orkpPBWgrdAfGX5cwz2oyXdB5m/JsylIloW4p0YMARj\nIhS58jZs9toeyTbdc3OzgmnCywKBgQDIZ4GgpcwQKy/S+RtwyT6GteGcZ5oGDkta\nNxIWKKZeUqtPDap8zN9zC4EDSXrnVzclU/xtOx0j5jeISUEERyysELXxaIMnBmqo\nV7iFjBYxqwzQr9xPbhUO6F3+P1EcBEqNE6sa3HcGqCJLrtgpJ/SVlCrH/XeETrEa\nL47S8wqYOwKBgFMlzLVMCMzj7fx6ttbWDZqG2t4Uzd25CTCIw2SZckK0V5QwObYT\n/IaMV4uLrpsJPdooJNQOEK1NqpWi887NglBW1l2rJgzMxo85ro5Rvmv5gOMlcUm5\nx04V/q+jjjq2pu2bxjWKLcXVfyNcS7owKgtWZq0vyoROZh8a3cbtWUD1AoGAT2qN\nOQ9a7bFfk1h6YlituY6MKIH5zJqPi+G0/ij/vPM/xeUhgbFif2G4r4fH97NdPCKr\nLtI1Owu5R/3XkQ1vi4RXxaHoD2gAjlOs4OdhyC8DthNq1+NE3xKAZQfMabDdpkh8\nDvn9BRtN8/YG3VBjIX+E8Jnmaf0RO9JK5BfCRA8CgYEAvUeG8Wcwhszdo4XGaEzW\n64yeWBnKS4ORitvbrrAIBS7oNWXSAS8gWiUvbwK+9sQOZ3X3aJ7cQgFuQC6wn9Tz\n0a6Gza6VPejb+ANwfIj5Ub5grvB4xzu6Y5FYUJvYdj82Nu6mo+NL6FlwZevJYepc\nOd4wj921u9Htr9kA5ARBVnw=\n-----END PRIVATE KEY-----\n",
      "client_email": "firebase-adminsdk-dlxlk@coachingapp-af070.iam.gserviceaccount.com",
      "client_id": "102687085420793657938",
      "auth_uri": "https://accounts.google.com/o/oauth2/auth",
      "token_uri": "https://oauth2.googleapis.com/token",
      "auth_provider_x509_cert_url": "https://www.googleapis.com/oauth2/v1/certs",
      "client_x509_cert_url": "https://www.googleapis.com/robot/v1/metadata/x509/firebase-adminsdk-dlxlk%40coachingapp-af070.iam.gserviceaccount.com"
    }),
    databaseURL: "https://coachingapp-af070.firebaseio.com"
  });

exports.migrateDb = functions.database.ref('MigrateDb').onWrite(event=>{
    var db = admin.firestore();
    return admin.database().ref('Class: XIIth').once('value')
    .then(snapshot=>{
        return snapshot.forEach((childSnapshot)=>{
            let childData = childSnapshot.val();
            childData.standard = 'XIIth';
            let key = childSnapshot.key
            db.collection('Toppers').doc().set(childData)
        })
    });
});

exports.migrateToppersXIIth = functions.database.ref('Class: XIIth/{topper}').onCreate((snapshot,context)=>{
    let data = snapshot.val();
    let name = data.name;
    console.log("name of the data is "+name);
    data.standard = "XIIth";
    var db = admin.firestore();
    return db.collection('Toppers').doc().set(data);
});

exports.migrateToppersXth = functions.database.ref('Class: Xth/{topper}').onCreate((snapshot,context)=>{
    let data = snapshot.val();
    let name = data.name;
    console.log("name of the data is "+name);
    data.standard = "Xth";
    var db = admin.firestore();
    return db.collection('Toppers').doc(name).set(data);
});

exports.migrateTeachers = functions.database.ref('Teachers/{teacher}').onCreate((snapshot,context)=>{
    let data = snapshot.val();
    let name = data.name;
    console.log("name of the data is "+name);
    var db = admin.firestore();
    return db.collection('Teachers').doc(name).set(data);
});

exports.migrateStudyMaterialCreate = functions.database.ref('StudyMaterial/{material}').onCreate((snapshot,context)=>{
    let data = snapshot.val();
    let key = snapshot.key;
    console.log("key of the data in onCreate is "+key);
    var db = admin.firestore();
    return db.collection('StudyMaterial').doc(key).set(data);
});

exports.migrateStudyMaterialUpdate = functions.database.ref('StudyMaterial/{subject}').onUpdate((change,context)=>{
    
    var db = admin.firestore();
    if(change.after.exists())
    {
        let data = change.after.val();
        let key = change.after.key;
        console.log("key of the data in onUpdate is "+key)
        console.log("data of onUpdate is "+data)
        return db.collection('StudyMaterial').doc(key).update(data);
    }
    if(!change.after.exists())
    {
        console.log("key of the data in onUpdate (delete)is "+change.before,key);
        let batch = db.batch();
        batch.delete(db.collection('StudyMaterial').doc(change.before.key))
        return batch.commit();
    }
    return null;
});

exports.migrateStudyMaterialDelete = functions.database.ref('StudyMaterial/{subject}').onDelete((snapshot,context)=>{
    var db = admin.firestore();
    let batch = db.batch();
    console.log('deleted is triggered '+snapshot.key)
    batch.delete(db.collection('StudyMaterial').doc(snapshot.key))
    return batch.commit();
})