// Q10: Stopping a Thread
// Create a program simulating a file download in a thread and gracefully stop it using a flag.

class DownloadTask implements Runnable {
    // A volatile flag ensures that changes made by one thread are immediately visible to others
    private volatile boolean stopFlag = false;

    @Override
    public void run() {
        int chunk = 1;
        // Continue downloading while the stop flag is FALSE
        while (!stopFlag) {
            System.out.println("Downloading chunk " + chunk + "...");
            chunk++;
            try {
                // Simulate time taken to download a chunk
                Thread.sleep(500); 
            } catch (InterruptedException e) {
                System.out.println("Download interrupted.");
                // Restore the interrupted status
                Thread.currentThread().interrupt();
                break; // Exit the loop if interrupted
            }
        }
        System.out.println("Download stopped gracefully.");
    }

    // Custom method to trigger the graceful stop
    // DO NOT use the deprecated Thread.stop() method
    public void stopDownload() {
        this.stopFlag = true;
    }
}

public class FileDownloadSimulation {
    public static void main(String[] args) {
        DownloadTask task = new DownloadTask();
        Thread downloadThread = new Thread(task);

        System.out.println("Starting download...");
        downloadThread.start();

        // Let the download run for 2 seconds
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("User triggered stop...");
        
        // Gracefully stop the thread by changing the flag
        task.stopDownload();
        
        // Wait for the thread to finish executing its current iteration
        try {
            downloadThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        System.out.println("Main thread exits.");
    }
}
