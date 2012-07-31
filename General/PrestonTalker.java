package General;

    import org.powerbot.concurrent.Task;
    import org.powerbot.concurrent.strategy.Condition;
    import org.powerbot.concurrent.strategy.Strategy;
    import org.powerbot.game.api.ActiveScript;
    import org.powerbot.game.api.Manifest;
    import org.powerbot.game.api.methods.input.Keyboard;
    import org.powerbot.game.api.util.Random;
    import org.powerbot.game.api.util.Time;
     
    @Manifest(authors = { "Preston3050" }, name = "Preston's Auto talker", description = "It just keeps on talking")
    public class PrestonTalker extends ActiveScript {
           
            String setText1 = "[Thereal Dyce] Hosting 57x2"; // Text 1.
            String setText2 = "Hosting Dicing!! [TheReal Dyce]"; // Text 2.
            String setText3 = "Legit hosting 57x2! [Thereal Dyce]"; // Text 3.
            String setText4 = "Hosting 57x2! [Thereal Dyce]"; // Text 4.
           
            String sendText = ""; // Leave this alone.
           
            private int sleepTime = 3000; // 1000 = 1 second.
     
            @Override
            protected void setup() {
                    talkHandler TALKHANDLER = new talkHandler();
                   
                    Strategy TALKHANDLE = new Strategy(TALKHANDLER, TALKHANDLER);
                   
                    provide(TALKHANDLE);
            }
           
            private String sendHandler() {
                   
                    int rand = (int) Random.nextDouble(1, 4);
                    switch(rand) {
                            case 1:
                                    sendText = setText1;
                            break;
                   
                            case 2:
                                    sendText = setText2;
                            break;
                   
                            case 3:
                                    sendText = setText3;
                            break;
                           
                            case 4:
                                    sendText = setText4;
                            break;
                           
                    }
                    return sendText;
            }
           
            private class talkHandler implements Task, Condition {
     
                    @Override
                    public void run() {
                            Keyboard.sendText(sendHandler(), true, 1,1);
     
                            Time.sleep(5000);
                           
                    }
     
                    @Override
                    public boolean validate() {
                            return true;
                    }
                   
            }
           
           
     
    }
