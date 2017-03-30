package mvp.model.mapping;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import mvp.model.entity.Photo;
import mvp.model.entity.PhotoEntity;
import mvp.model.utils.Constants;

/**
 * Created by Manuel Lorenzo on 19/03/2017.
 */

public class PhotoEntityMapper implements PhotoEntityMapperInterface {
    @Override
    public List<PhotoEntity> transform(Collection<Photo> photoCollection) {
        ArrayList<PhotoEntity> photoEntityArrayList = new ArrayList<>();
        int count = 0;
        Iterator it = photoCollection.iterator();
        while (it.hasNext() && count < photoCollection.size() / Constants.NUMBER_MATCHING_CARDS) {
            ++count;
            Photo photo = (Photo) it.next();
            PhotoEntity photoEntity = new PhotoEntity();
            photoEntity.setId(photo.getId());
            String photoUrlString = "https://farm" + photo.getFarm() + ".staticflickr.com/" + photo.getServer() + "/" + photo.getId() + "_" + photo.getSecret() + ".jpg";
            photoEntity.setUrl(photoUrlString);
            photoEntityArrayList.add(photoEntity);
        }
        ArrayList<PhotoEntity> newPhotoEntityArrayList1 = new ArrayList<>();
        for (int i = 0; i < count / (2 * Constants.NUMBER_MATCHING_CARDS); i++) {
            newPhotoEntityArrayList1.addAll(photoEntityArrayList);
        }
        return newPhotoEntityArrayList1;
    }
}
