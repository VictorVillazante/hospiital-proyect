// package com.example.monolitoproyect.StateDessignPattern.service.impl;


// import java.util.ArrayList;
// import java.util.HashMap;
// import java.util.List;
// import java.util.Map;
// import java.util.concurrent.ExecutionException;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// import com.example.monolitoproyect.Firebase.FirebaseInitializer;
// import com.example.monolitoproyect.StateDessignPattern.dto.PostDTO;
// import com.google.api.core.ApiFuture;
// import com.google.cloud.firestore.CollectionReference;
// import com.google.cloud.firestore.DocumentSnapshot;
// import com.google.cloud.firestore.QuerySnapshot;
// import com.google.cloud.firestore.WriteResult;

// @Service
// public class PostManagementServiceImpl{
//     // @Autowired
//     // private FirebaseInitializer firebase
//     private FirebaseInitializer firebase=new FirebaseInitializer();

//     public List<PostDTO> list() {
//         List<PostDTO> response =new ArrayList<>();
//         PostDTO post;
//         ApiFuture<QuerySnapshot>QSAF=firebase.getFirestore().collection("prueba").get();
//         try {
//             for(DocumentSnapshot doc:QSAF.get().getDocuments()){
//                 post=(doc.toObject(PostDTO.class));
//                 post.setId(doc.getId());
//                 response.add(post);
//             }
//             return response;
//         } catch (Exception e) {
//             // TODO Auto-generated catch block
//             return null;            
//         }
//     }

//     public Boolean add(PostDTO post) {
//         // TODO Auto-generated method stub
//         Map<String,Object> docData=new HashMap<>();
//         docData.put("title",post.getTitle());
//         docData.put("content",post.getContent());
//         CollectionReference posts =firebase.getFirestore().collection("prueba");
//         ApiFuture<WriteResult> wraf=posts.document().create(docData);
//         try {
//             if (wraf.get()!=null){
//                 return Boolean.TRUE;
//             }
//             return Boolean.FALSE;
//         } catch (Exception e) {
//             return Boolean.FALSE;
//         }
//     }
    
// }
