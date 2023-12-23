package generic;

// Common practice:
// E - Element (usually for collection)
// K - Key
// T - Type
// N - Number
// V - Value
// S, U, V, etc., for 2nd, 3rd, 4th types
public class GenericData<T> {
    // Type : could be string, long, etc
    private T data;

    public GenericData(T data) {
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

