package pl.retsuz.filesystem;

public interface IComposite
{
    void setName(String name);

    String getName();

    IComposite getParent();

    void setParent(IComposite param);

    String getPath();
}