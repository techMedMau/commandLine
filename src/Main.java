import com.company.directory.Directory;
import com.company.file.File;

import java.util.Scanner;

public class Main {
    public static class CommandSolver{
        private static abstract class Command{
            private final String key;
            private final int argSize;

            public Command(String key, int argSize){
                this.key = key;
                this.argSize = argSize;
            }

            public boolean isCorrect(String str){
                if(!str.startsWith(this.key)){return false;}

                if(!hasArg()){return true;}

                String[] result = str.split(" ");
                if(result.length < this.argSize+1){
                    //if amount of argument is not enough, return false
                    return false;
                }

                return true;
            }

            public boolean hasArg(){
                return argSize != 0;
            }

            public String[] getArgs(String str){
                if(!isCorrect(str)){return null;}
                if(!hasArg()){return new String[]{};}
                String[] tmp = str.split(" ");
                String[] res = new String[this.argSize];
                for(int i = 0; i < res.length; i++){
                    res[i] = tmp[i+1];
                }
                return res;
            }

            public Directory exec(Directory dir, String opt){
                String[] args = getArgs(opt);
                if(args == null){
                    return dir;
                }
                return run(dir, args);
            }

            abstract Directory run(Directory dir, String[] args);
        }

        private static final Command[] COMMANDS = {
                new Command("cd", 1){
                    @Override
                    Directory run(Directory dir, String[] args){
                        Directory d = dir.get(args[0]);
                        if(d == null) return dir;
                        return d;
                    }
                },
                new Command("cd..", 0){
                    @Override
                    Directory run(Directory dir, String[] args){
                        if(dir.getParent() == null) return dir;
                        return dir.getParent();
                    }
                },
                new Command("mkdir", 1){
                    @Override
                    Directory run(Directory dir, String[] args){
                        dir.add(new Directory(args[0], dir));
                        return dir;
                    }
                },
                new Command("touch", 1){
                    @Override
                    Directory run(Directory dir, String[] args){
                        dir.add(new File(args[0], dir));
                        return dir;
                    }
                },
                new Command("search", 1){
                    @Override
                    Directory run(Directory dir, String[] args){
                        dir.search(args[0]);
                        return dir;
                    }
                },
                new Command("ls", 0){
                    @Override
                    Directory run(Directory dir, String[] args){
                        dir.ls();
                        return dir;
                    }
                }
        };

        public Directory accept(Directory dir, String opt){
            Command cmd = isExisted(opt);
            if(cmd == null)return dir;
            return cmd.exec(dir, opt);
        }

        public Command isExisted(String command){
            for(int i = 0; i < COMMANDS.length; i++){
                if(COMMANDS[i].isCorrect(command)){
                    return COMMANDS[i];
                }
            }
            return null;
        }
    }

    public static void main(String args[]){
        Scanner scanner = new Scanner(System.in);
        Directory current = new Directory("root");
        CommandSolver cs = new CommandSolver();

        while(true){
            System.out.print(current.getPath()+">");
            String opt = scanner.nextLine();
            current = cs.accept(current, opt);
        }



    }
}
