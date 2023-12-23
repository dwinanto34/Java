package generic;

// only allow types that extend Number
public class BoundedGenericData<T extends Number> {
    private T data;

    public BoundedGenericData(T data) {
        this.data = data;
    }

    // setter
    public void setData(T data) {
        this.data = data;
    }

    // getter
    public T getData() {
        return data;
    }
}
