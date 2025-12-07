package latihan2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadLocalRandom;

public class DownloadGUI extends JFrame {

    private final JProgressBar pb1 = new JProgressBar(0, 100);
    private final JProgressBar pb2 = new JProgressBar(0, 100);
    private final JProgressBar pb3 = new JProgressBar(0, 100);
    private final JButton btn = new JButton("Downloading");

    public DownloadGUI() {
        setTitle("Download App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(750, 330);
        setLocationRelativeTo(null);
        initUI();
    }

    private void initUI() {
        JLabel header = new JLabel("Download App", SwingConstants.CENTER);
        header.setFont(new Font("SansSerif", Font.BOLD, 18));
        header.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        JPanel center = new JPanel(new GridBagLayout());
        center.setBorder(BorderFactory.createEmptyBorder(10, 80, 10, 80));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(12, 10, 12, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        addRow(center, gbc, 0, "File 1", pb1);
        addRow(center, gbc, 1, "File 2", pb2);
        addRow(center, gbc, 2, "File 3", pb3);

        JPanel bottom = new JPanel(new BorderLayout());
        bottom.setBorder(BorderFactory.createEmptyBorder(10, 80, 20, 80));
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btn.setPreferredSize(new Dimension(130, 32));
        btnPanel.add(btn);
        bottom.add(btnPanel, BorderLayout.EAST);

        setLayout(new BorderLayout());
        add(header, BorderLayout.NORTH);
        add(center, BorderLayout.CENTER);
        add(bottom, BorderLayout.SOUTH);

        btn.addActionListener(this::startDownloads);
    }

    private void addRow(JPanel panel, GridBagConstraints gbc, int row, String label, JProgressBar pb) {
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.weightx = 0;
        panel.add(new JLabel(label), gbc);

        gbc.gridx = 1;
        gbc.weightx = 1;
        pb.setPreferredSize(new Dimension(450, 22));
        pb.setStringPainted(false);
        panel.add(pb, gbc);
    }

    private void startDownloads(ActionEvent e) {
        btn.setEnabled(false);

        CountDownLatch latch = new CountDownLatch(3);
        new DownloadWorker(pb1, latch).execute();
        new DownloadWorker(pb2, latch).execute();
        new DownloadWorker(pb3, latch).execute();

        new Thread(() -> {
            try {
                latch.await();
            } catch (InterruptedException ignored) {}
            SwingUtilities.invokeLater(() -> btn.setEnabled(true));
        }).start();
    }

    private static class DownloadWorker extends SwingWorker<Void, Integer> {
        private final JProgressBar pb;
        private final CountDownLatch latch;

        public DownloadWorker(JProgressBar pb, CountDownLatch latch) {
            this.pb = pb;
            this.latch = latch;
        }

        @Override
        protected Void doInBackground() {
            int delay = ThreadLocalRandom.current().nextInt(250, 600);
            for (int i = 0; i <= 100; i += 5) {
                try {
                    Thread.sleep(delay);
                } catch (InterruptedException ignored) {}
                publish(i);
            }
            return null;
        }

        @Override
        protected void process(java.util.List<Integer> chunks) {
            pb.setValue(chunks.get(chunks.size() - 1));
        }

        @Override
        protected void done() {
            latch.countDown();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new DownloadGUI().setVisible(true));
    }
}
