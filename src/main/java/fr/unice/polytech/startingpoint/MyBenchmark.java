package fr.unice.polytech.startingpoint;

import fr.unice.polytech.startingpoint.io.AnalyseEntree;
import fr.unice.polytech.startingpoint.io.Entree;
import fr.unice.polytech.startingpoint.grille.Carte;
import fr.unice.polytech.startingpoint.main.Algo;
import fr.unice.polytech.startingpoint.main.Score;
import fr.unice.polytech.startingpoint.io.Sortie;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.profile.StackProfiler;
import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.OptionsBuilder;


import java.io.*;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
//@Fork(value = 2, jvmArgs = {"-Xms2G", "-Xmx2G"})
@Fork ()
@Warmup(iterations = 2)
@Measurement(iterations = 5)

public class MyBenchmark {

    private Scanner sc;
    private Entree entree;
    private Score score;
    private Algo algo;
    private AnalyseEntree analyseEntree;
    private Sortie sortie;
    private Carte carte;

    public static void main(String[] args) throws RunnerException {


        org.openjdk.jmh.runner.options.Options opt = new OptionsBuilder()
                .include(MyBenchmark.class.getSimpleName())
                .shouldDoGC(true)
                .resultFormat(ResultFormatType.JSON)
                .result(".result/result" + System.currentTimeMillis() +".JSON")

                .addProfiler(StackProfiler.class)
                .jvmArgsAppend("-Dmjh.stack.period=1")
                .forks(1)
                .build();

        new Runner(opt).run();

    }

    @Setup
    public void setup() throws FileNotFoundException {
        sc = new Scanner(new File("input_files/input5.in"));
        entree = new Entree(new AnalyseEntree(sc));
        score = new Score(entree.getPrixDistance(), entree.getPrixConstant(), entree.getBonus());
        algo = new Algo(entree, score);
        carte = entree.getCarte();
        analyseEntree = new AnalyseEntree(sc);
        sortie = new Sortie(score, carte);
    }

    @Benchmark
    public void processing() throws IOException{
        algo.traitement();
    }

    @Benchmark
    public void Entree() throws FileNotFoundException {
        Entree entreeTest;
        entreeTest = new Entree(new AnalyseEntree(new Scanner(new File("input_files/input5.in"))));
    }


    @Benchmark
    public void sortie() { sortie.creerSortie(); }


    @Benchmark
    public void Main() throws FileNotFoundException {
        Scanner sc = new Scanner(new File("input_files/input5.in"));
        AnalyseEntree analyseEntree = new AnalyseEntree(sc);
        Entree entree = new Entree(analyseEntree);
        sc.close();

        Carte carte = entree.getCarte();
        Score score = new Score(entree.getPrixDistance(), entree.getPrixConstant(), entree.getBonus());
        Algo algo = new Algo(entree, score);


        algo.traitement();


        Sortie sortie = new Sortie(score, carte);
        sortie.creerSortie();

    }


}
