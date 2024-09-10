package br.com.empiricus.EmpiricusADM.Service;

import br.com.empiricus.EmpiricusADM.Model.User;
import br.com.empiricus.EmpiricusADM.Repository.UserRepository;
import br.com.empiricus.EmpiricusADM.Service.Exceptions.ItemNotFound;
import br.com.empiricus.EmpiricusADM.Service.Exceptions.MismatchId;
import br.com.empiricus.EmpiricusADM.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repo;


    public List<UserDTO> findAllUsers() {
        return repo.findAll().stream().map(UserDTO::new).collect(Collectors.toList());
    }

    public UserDTO findUserById(Long id) {
        User user = repo.findById(id)
                .orElseThrow(() -> new ItemNotFound("User exception id: " + id));
        return new UserDTO(user);
    }

    public UserDTO createUser(User user) {
        return new UserDTO(repo.save(user));
    }

    public UserDTO updateUser(Long id, User user) {
        if(!Objects.equals(id, user.getId())){
            throw new MismatchId("Id passed on path "+ id+" isn't equal to id passed onto the object "+ user.getId());
        }
        User item = repo.getReferenceById(id);
        update(item, user);
        return new UserDTO(repo.save(item));
    }

    private void update(User item, User user) {
        item.setNome(user.getNome());
        item.setPassword(user.getPassword());
        item.setData_atualizacao(user.getData_atualizacao());
        item.setEh_admin(user.isEh_admin());
    }

    public void deleteUser(Long id) {
        if(findUserById(id)!=null){

        repo.deleteById(id);
        } else {
            throw new ItemNotFound("Item id: "+id+" was not found");
        }
    }


}
