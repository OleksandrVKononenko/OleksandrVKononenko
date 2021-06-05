package ap.test;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

public class ScrollTest {
  // custom Textarea class
  static class MyTextArea extends JTextArea {
    @Override
    public void scrollRectToVisible(final Rectangle aRect) {
      // supress scrollToRect in textarea
    }
  }

  static final Box inner = Box.createVerticalBox();

  public static void main(final String[] args) {
    final JFrame frame = new JFrame();

    final JPanel insideScroll = insideScroll();
    final JScrollPane scrollpane = new JScrollPane(insideScroll);
    scrollpane.setAutoscrolls(false);
    scrollpane
    .setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
    final JPanel rightPanel = new JPanel();
    rightPanel.setPreferredSize(new Dimension(300, 300));

    final MouseMotionAdapter listener = new MouseMotionAdapter() {

      @Override
      public void mouseDragged(final MouseEvent e) {
        super.mouseDragged(e);

        update();
      }
    };
    rightPanel.addMouseMotionListener(listener);

    // Add labels describing the problem
    rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.PAGE_AXIS));

    final JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
        scrollpane, rightPanel);

    frame.add(split);

    // initialize the scrollpane content
    update();

    frame.pack();
    frame.setVisible(true);

    frame.setLocationRelativeTo(null);

  }

  // initializes the components inside the scrollpane
  private static JPanel insideScroll() {
    final JPanel panel = new JPanel();
    panel.setLayout(new BorderLayout());
    panel.add(inner, BorderLayout.NORTH);

    return panel;
  }

  // replaces all components inside the scrollpane
  private static void update() {
    inner.removeAll();

    for (int i = 0; i < 30; i++) {
      inner.add(buildRow(i));
    }

    inner.revalidate();
  }

  // build a single component to be inserted into the scrollpane
  private static JPanel buildRow(final int i) {
    final JPanel row = new JPanel();

    final Color bg = i % 2 == 0 ? Color.DARK_GRAY : Color.LIGHT_GRAY;

    row.setBackground(bg);
    row.setPreferredSize(new Dimension(300, 80));
    row.setLayout(new BorderLayout());

    row.add(textarea(bg), BorderLayout.CENTER);

    return row;
  }

  // build the textarea to be inserted into the cells in the scroll pane
  private static Component textarea(final Color bg) {
    final String text = String.format("%d", (int) (1000 * Math.random()))
        + " Lorem ipsum dolor si amet. Lorem ipsum dolor si amet. Lorem ipsum dolor si amet";

    final JTextArea textarea = new MyTextArea();
    textarea.setDisabledTextColor(Color.cyan);

    textarea.setEnabled(false);
    textarea.setLineWrap(true);
    textarea.setBackground(bg);
    textarea.setEditable(false);
    textarea.setText(text);

    return textarea;
  }

}