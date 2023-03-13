package com.restful.webservices.restfulwebservices.user;

import com.restful.webservices.restfulwebservices.user.jpa.PostRepository;
import com.restful.webservices.restfulwebservices.user.jpa.UserRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class UserJpaResource {
    private UserDaoService userDaoService;

    private UserRepository repository;

    private PostRepository postRepository;
    public UserJpaResource(UserDaoService userDaoService, UserRepository repository, PostRepository postRepository) {
        this.userDaoService = userDaoService;
        this.repository = repository;
        this.postRepository = postRepository;
    }

    @GetMapping("/jpa/users")
    public List<User> retriveAllUsers() {
        return repository.findAll();
    }

    @GetMapping("/jpa/users/{id}")
    public Optional<User> retriveUsers(@PathVariable Integer id) {
        Optional<User> user = repository.findById(id);
        if(user.isEmpty()) {
            throw new UserNotFoundException("id"+id);
        }
        return user;
    }
    @DeleteMapping("/jpa/users/{id}")
    public void deleteUsers(@PathVariable Integer id) {
        repository.deleteById(id);
    }

    @GetMapping("/jpa/users/{id}/posts")
    public List<Post> retrieveUsersPosts(@PathVariable Integer id) {
        Optional<User> user = repository.findById(id);
        if(user.isEmpty()) {
            throw new UserNotFoundException("id"+id);
        }
        return user.orElseThrow().getPostList();
    }

    @PostMapping("/jpa/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {

        User savedUser = repository.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(location).build();
    }
    @PostMapping("/jpa/users/{id}/posts")
    public ResponseEntity<Object> creatPost(@PathVariable Integer id, @Valid @RequestBody Post post) {
        Optional<User> user = repository.findById(id);
        if(user.isEmpty()) {
            throw new UserNotFoundException("id"+id);
        }
        post.setUser(user.get());
        Post savedPost = postRepository.save(post);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(savedPost.getId()).toUri();
        return ResponseEntity.created(location).build();

    }

}
