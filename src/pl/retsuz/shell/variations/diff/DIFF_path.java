package pl.retsuz.shell.variations.diff;

import pl.retsuz.filesystem.Composite;
import pl.retsuz.filesystem.IComposite;
import pl.retsuz.filesystem.TextFile;
import pl.retsuz.shell.gen.ICommand;
import pl.retsuz.shell.variations.gen.CommandVariation;
import pl.retsuz.shell.variations.gen.ICommandVariation;

public class DIFF_path extends CommandVariation
{
    public DIFF_path(ICommandVariation next, ICommand parent)
    {
        super(next, parent, "[a-zA-Z0-9.l\\/_]* [a-zA-Z0-9.l\\/_]*");
    }

    @Override
    public void make(String params)
    {
        Composite c = (Composite) (this.getParent().getContext().getCurrent());
        try
        {
            IComposite elem = c.findElementByPath(params.split(" ")[0]);
            IComposite elem2 = c.findElementByPath(params.split(" ")[1]);
            if (TextFile.class.isInstance(elem) && TextFile.class.isInstance(elem2))
            {
                System.out.println(((TextFile) elem).diff(elem2));
            }
            else
            {
                System.err.println("Żądany element nie jest plikiem.");
            }

        }
        catch (Exception e)
        {
            System.err.println("Docelowy element nie jest katalogiem lub obecny katalog nie zawiera elementu.");
        }
    }
}