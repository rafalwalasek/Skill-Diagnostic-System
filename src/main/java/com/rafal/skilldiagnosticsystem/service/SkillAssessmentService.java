
//    public List<String> showPerformance(List<Double> results) {
//        List<String> performances = new ArrayList<>();
//
//        double sum = 0.0;
//        double max = 0.0;
//        for (Double result : results) {
//            sum = sum + result;
//            if (result > max) {
//                max = result;
//            }
//        }
//
//        performances.add(String.format("%.0f", sum / results.size()));
//        performances.add(String.format("%.0f", max));
//
//        if (results.isEmpty()) {
//            performances.add("Brak danych");
//        } else if (results.getFirst() > results.getLast()) {
//            performances.add("Malejący");
//        } else if (results.getFirst() < results.getLast()) {
//            performances.add("Rosnący");
//        } else performances.add("Stały");
//
//        return performances;
//    }
