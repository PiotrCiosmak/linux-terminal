package pl.retsuz.context;

import pl.retsuz.filesystem.IComposite;

public interface IContext
{
    void setCurrent(IComposite current);

    IComposite getCurrent();
}