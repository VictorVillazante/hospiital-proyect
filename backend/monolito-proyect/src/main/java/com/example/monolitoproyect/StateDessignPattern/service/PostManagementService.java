package com.example.monolitoproyect.StateDessignPattern.service;


import java.util.List;

import com.example.monolitoproyect.StateDessignPattern.dto.PostDTO;

public interface PostManagementService {
    List<PostDTO>list();
    Boolean add(PostDTO post);
    
}
