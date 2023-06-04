package projects;

import java.util.List;
import java.util.UUID;

/**
 * @author thomp (04/06/2023)
 */
public abstract class SimpleDAO<T> {
    //@Getter
    //private final Datastore datastore;
    private final String datastore;

    public SimpleDAO(String datastore) {
        this.datastore = datastore;
    }

    public T save(T entity) {
        //entity = datastore.save(entity);
        return entity;
    }

    /*public T get(String id) {
        return datastore.find(getEntityClass()).filter(Filters.eq("_id", id)).first();
    }

    public T get(UUID uuid) {
        return datastore.find(getEntityClass()).filter(Filters.eq("_id", uuid)).first();

    }

    public List<T> findAll(String value, String equals) {
        return datastore.find(getEntityClass()).filter(Filters.eq(value, equals)).stream().toList();
    }

    public T findFirst(String value, String equals) {
        return findAll(value, equals).get(0);
    }

    public void delete(String id) {
        Query<T> query = datastore.find(getEntityClass()).filter(Filters.eq("_id", id));
        T first = query.first();

        if (first != null) datastore.delete(first);
    }

    public T findLast() {
        List<T> results = datastore.find(getEntityClass()).stream().toList();

        return results.get(results.size() - 1);
    }

    public List<T> findAll() {
        return datastore.find(getEntityClass()).stream().toList();
    }
*/

    protected abstract Class<T> getEntityClass();
}

