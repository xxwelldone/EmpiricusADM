package br.com.empiricus.EmpiricusADM.Service;

import br.com.empiricus.EmpiricusADM.Model.User;
import br.com.empiricus.EmpiricusADM.Repository.UserRepository;
import br.com.empiricus.EmpiricusADM.Service.Exceptions.ItemNotFound;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repo;

    public List<User> findAllUsers(){
        return repo.findAll();
    }
    public User findUserById(Long id){
        return repo.findById(id)
                .orElseThrow(()->new ItemNotFound("User exception id: "+id));
    }
    public User createUser(User user){
        return  repo.save(user);
    }
    public User UpdateUser(Long id, User user){
    User item = repo.getReferenceById(id);
    update(item, user);
    return repo.save(item);
    }

    private void update(User item, User user) {
        item.setNome(user.getNome());
        item.setPassword(user.getPassword());
        item.setData_atualizacao(user.getData_atualizacao());
        item.setEh_admin(user.isEh_admin());
    }
    public void DeleteUser(Long id){
        repo.deleteById(id);
    }


}
