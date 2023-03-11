public interface SerializeDeserializeInterface {
    void serialize(Object object, String file);

    Object deSerialize(String file);
}
