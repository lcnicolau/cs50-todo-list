package io.github.lcnicolau.cs50.todolist.planner;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

import static io.github.lcnicolau.cs50.todolist.planner.Planner.ADMIN;
import static io.github.lcnicolau.cs50.todolist.planner.Planner.USER;

@Component
@RequiredArgsConstructor
class PlannerInitializer {

    private final PlannerRepository plannerRepository;


    @PostConstruct
    void init() {
        USER.setId(plannerRepository.save(USER).getId());
        USER.eraseCredentials();
        ADMIN.setId(plannerRepository.save(ADMIN).getId());
        ADMIN.eraseCredentials();
        plannerRepository.saveAll(getDummyUsers().toList());
    }

    static Stream<Planner> getDummyUsers() {
        return Stream.of(
                new Planner("User 00", "user00@todolist.com", "$2a$10$Vr2qVU9ic8BfvNnbWuf83.SmL1wQBA4fOSdTh4Nk.w1vEL3iPG8Gq", false),
                new Planner("User 01", "user01@todolist.com", "$2a$10$XjBWLJS3jVf9DPlQvO8acumLq0JYbazyZInXZFJ/HL5sL6kJcdK/.", false),
                new Planner("User 02", "user02@todolist.com", "$2a$10$gUqeVng/k9czIex7jBehtuhpglQx.glBxMepLANWFOp7d17iUmIKC", false),
                new Planner("User 03", "user03@todolist.com", "$2a$10$GALnecIiwPdn0ZQT1RBGeuoj4gqY60Gk95GiXF.31DcOSxfqH5/02", false),
                new Planner("User 04", "user04@todolist.com", "$2a$10$.zXFh8RU5HYKrQ5z8YiLteVwSiBJsBZpIUXYaFCKVXsWF7nMf3YYe", false),
                new Planner("User 05", "user05@todolist.com", "$2a$10$uOu3nRk/H8Op0H2O6tv6l.Igg1mlbnvVxMb7XoejR9PHR3qf/IADW", false),
                new Planner("User 06", "user06@todolist.com", "$2a$10$lyVmyHf2DD6YCIf43UTIFOPkygxIO/aq1fQU2LFUfPYjkgLrgHRYy", false),
                new Planner("User 07", "user07@todolist.com", "$2a$10$.OMub0SMkbuqIU6e24oXSehstyl/1pv/sHcRQMqPmnvZEyMpEuJ5e", false),
                new Planner("User 08", "user08@todolist.com", "$2a$10$nzXSH/L2tzYOPMGE8Dz/d.PIQSEr4nCI1uCAgGRkNb6HbJTeOkBH.", false),
                new Planner("User 09", "user09@todolist.com", "$2a$10$wQga7sYisK4Ak3ht3owFsev8./j6seTRvLyLzpFO0cF5WU.HvJG9i", false),
                new Planner("User 10", "user10@todolist.com", "$2a$10$fK1XE3Wy8v3NH8UsokaaUO6UnSrligYToMTKZoCZNBUPJE9Wb1hxC", false),
                new Planner("User 11", "user11@todolist.com", "$2a$10$V46LjtFDKrprJGCDJIywXe/H3xQJer0U8Ix0i1X0g81QAq80UjbVO", false),
                new Planner("User 12", "user12@todolist.com", "$2a$10$6kZi1tW7Lpr1crTujd9i7esv3ZT9ABGrD/rvcwXbPnombxCDvl4jO", false),
                new Planner("User 13", "user13@todolist.com", "$2a$10$hcX91ayjigcencurBdGpUO25nguo9w/dGtEOZnEHqQ2sJMH.PuZUi", false),
                new Planner("User 14", "user14@todolist.com", "$2a$10$ib1FDr8KqfE0njghm3mRweYgvwxfwQQb4073X4vUJFB6RAe1jaxoS", false),
                new Planner("User 15", "user15@todolist.com", "$2a$10$KXQZtXmJEarJ.Dtki1trL.BKVE7.rlwoHiFC4sHyE4OMUO5DHFsdO", false),
                new Planner("User 16", "user16@todolist.com", "$2a$10$pFY7hzn28dVLyIFNBqWqae1c4ai4i8/G2ZRe16GoO7smeWjcsOr12", false),
                new Planner("User 17", "user17@todolist.com", "$2a$10$.vtbB/t.EImyetzlPtoFgeqqMrTc7gnUqPgMOnZ.v.HoTRuN0sNj6", false),
                new Planner("User 18", "user18@todolist.com", "$2a$10$fneapcP5kk2ZS5lw7JavoO8Q1/kDiXjxMGZxNzk2WkXpPgS.hjfiK", false),
                new Planner("User 19", "user19@todolist.com", "$2a$10$Zm1r52rjU2ApcWIWWhWsz.37vHb6e3ngom0SbHzcxTuzbmMjxkmf.", false),
                new Planner("User 20", "user20@todolist.com", "$2a$10$5Ka2ghizWS2u.rGUQiESputhOifBLD0j.osw8yQIPexQIBdF0BxU.", false),
                new Planner("User 21", "user21@todolist.com", "$2a$10$SiRyi4HlvEc0DZNNM4voi.l3ZT.JInxX0LRjI8IlfRwTwJqQLSynm", false),
                new Planner("User 22", "user22@todolist.com", "$2a$10$GkoDmExdgCrUSa9NlGlm.ulmFsEHwNPgDclD3yN1mies4XStZNR4a", false),
                new Planner("User 23", "user23@todolist.com", "$2a$10$yhUsCADpfC.oaYadi2.sfOHY/lO/NsPrjPOnmhRtTt.tJdpxwMcpG", false),
                new Planner("User 24", "user24@todolist.com", "$2a$10$ae9A9bF1voaQDcRXMJA1pegff99IzDgzgEMRGFLfoAOcgiR931Toy", false),
                new Planner("User 25", "user25@todolist.com", "$2a$10$Ug9q3sVOZgZKfm/qshyoeuwYXyxtnoXjDTPXTT9b7bUf4YiAyAGW6", false),
                new Planner("User 26", "user26@todolist.com", "$2a$10$bHMoXArZSw9oiN6WKT3BoOryaqnasYqyieJ8WS261LzPdBsRw24Ua", false),
                new Planner("User 27", "user27@todolist.com", "$2a$10$7EeWH4ZpIfm6WchfqKA6nuqU.JMO09LnUWrJtKdHjpChU5DOU/Ozy", false),
                new Planner("User 28", "user28@todolist.com", "$2a$10$qevCFcHdqGnoWUBOhqCzJ.6xZk.FD.Sfvu6.0eDBpfPgZJAFE9pw.", false),
                new Planner("User 29", "user29@todolist.com", "$2a$10$anpq7RDjHV8Va4L.GvjFme0K6hxf1w3OHIgPgcK4zvtI/DOPZLYvm", false),
                new Planner("User 30", "user30@todolist.com", "$2a$10$pk3bfGd/lqD9dKkRcPTzK.6vqVS51s1k97lTEQsCi/C90/e2YbqKa", false)
        );
    }

}
