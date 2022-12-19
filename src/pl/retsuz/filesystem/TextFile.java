package pl.retsuz.filesystem;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TextFile extends GeneralComposite
{
    String content;

    public String getContent()
    {
        return content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    public String grep(String find_pattern)
    {
        String[] lines = this.content.split("\n");
        String result = "";
        for (String line : lines)
        {
            if (line.contains(find_pattern))
            {
                result += line + "\n";
            }
        }
        if (result == "")
        {
            return "Nie znaleziono wzorca";
        }
        return result;
    }

    public String diff(IComposite textFile)
    {
        List<String> leftLines = Arrays.asList(this.content.split("\n"));
        List<String> rightLines = Arrays.asList(((TextFile) textFile).content.split("\n"));
        List<String> diffLines = new ArrayList<>();

        int m = leftLines.size();
        int n = rightLines.size();
        int[][] lcs = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++)
        {
            for (int j = 0; j <= n; j++)
            {
                if (i == 0 || j == 0)
                {
                    lcs[i][j] = 0;
                }
                else if (leftLines.get(i - 1).equals(rightLines.get(j - 1)))
                {
                    lcs[i][j] = lcs[i - 1][j - 1] + 1;
                }
                else
                {
                    lcs[i][j] = Math.max(lcs[i - 1][j], lcs[i][j - 1]);
                }
            }
        }

        int i = m;
        int j = n;
        while (i > 0 && j > 0)
        {
            if (leftLines.get(i - 1).equals(rightLines.get(j - 1)))
            {
                diffLines.add(0, leftLines.get(i - 1));
                i--;
                j--;
            }
            else if (lcs[i - 1][j] > lcs[i][j - 1])
            {
                diffLines.add(0, "-" + leftLines.get(i - 1));
                i--;
            }
            else
            {
                diffLines.add(0, "+" + rightLines.get(j - 1));
                j--;
            }
        }

        while (i > 0)
        {
            diffLines.add(0, "-" + leftLines.get(i - 1));
            i--;
        }
        while (j > 0)
        {
            diffLines.add(0, "+" + rightLines.get(j - 1));
            j--;
        }

        return diffLines.toString();
    }
}