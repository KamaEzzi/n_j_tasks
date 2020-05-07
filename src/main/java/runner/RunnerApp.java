package runner;

import java.io.File;

public class RunnerApp {

    public static void main(String[] args) {


        TaskRunner runner = new TaskRunner();

        runner.add(new FileTask("file_32",2));
        runner.add(new Task() {
            @Override
            public int getPriority() {
                return 0;
            }

            @Override
            public void run() {
                String[] files = new File("./").list();

                if(files == null){
                    System.out.println("");
                } else  {
                    for (String f : files) {
                        System.out.println(f);
                    }
                }

            }
        });
        runner.add(new FileTask("file_1"));

        runner.start();
    }


    private static class FileTask implements Task {

        private String path;
        private int p = 1;

        public FileTask(String path) {
            this.path = path;
        }

        public FileTask(String path, int p) {
            this.path = path;
            this.p = p;
        }

        @Override
        public int getPriority() {
            return p;
        }

        @SuppressWarnings("ResultOfMethodCallIgnored")
        @Override
        public void run() {
            try {
                File f = new File(path + ".txt");
                f.createNewFile();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
