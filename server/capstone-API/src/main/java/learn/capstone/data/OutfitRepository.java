package learn.capstone.data;

import learn.capstone.models.Outfit;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface OutfitRepository {
    List<Outfit> findAll();

    Outfit findById(int outfitId);

    Outfit add(Outfit outfit);

    boolean update(Outfit outfit);

    @Transactional
    boolean deleteById(int outfitId);
}
