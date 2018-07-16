import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.tools.model.TipoInformacao;

public class Main {
	public static void main(String[] args) {
		Main main = new Main();
		Stream<File> streamFile = main.readFileIn();
		streamFile.forEach((file) -> {
			main.writeFileOut(file);
		});
	}
	

	private Stream<File> readFileIn() {
		Path folderIn = Paths.get("dados/in");
		File[] myFile = folderIn.toFile().listFiles();
		List<File> listFile = Arrays.asList(myFile);
		return listFile.stream()
				.filter(
					f -> f.getName().endsWith(".dat")
				);
	}

	private void writeFileOut(File file) {
		Path path = Paths.get("dados/out").resolve(
				file.getName() + ".proc");
		List<String> lines = new ArrayList<>();
		try {
            lines = Files.readAllLines(file.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
		try {
			BufferedWriter bufferedWriter = new BufferedWriter
			  (new FileWriter(path.toFile()));
			bufferedWriter.write("1. Quantidade de Clientes: " + 
				lines.stream().filter(t -> t.startsWith("002")).count());
			bufferedWriter.newLine();
			bufferedWriter.write("2. Quantidade de Vendedores: " + 
					lines.stream().filter(t -> t.startsWith("001")).count());
			bufferedWriter.newLine();
			TreeSet<Sale> sales = this.listSales(lines);
			bufferedWriter.write("3. ID da Venda de valor mais alto: " + sales.last().getId());
			bufferedWriter.newLine();
			bufferedWriter.write("4. Nome do Vendedor que menos vendeu: " + sales.first().getSellerName());
			bufferedWriter.close();
		} catch (IOException e) {
		    e.printStackTrace();
		}
	}


	private TreeSet<Sale> listSales(List<String> lines) {
		List<String> linesSales = lines
                .stream()
                    .filter(t -> t.startsWith("003"))
                    .collect(Collectors.toList());
		List<Sale> salesList = new ArrayList<>();
        for (String line : linesSales) {
            try {
                String[] info = line.split(";");
                Long id = Long.valueOf(info[1]);
                Long idItem = Long.valueOf(info[2]);
                Long qtyItems = Long.valueOf(info[3]);
                Double priceItem = Double.valueOf(info[4]);
                String sellerName = info[5];

                salesList.add(new Sale(id, idItem, qtyItems, priceItem, sellerName));
            } catch (Exception e) {
            	e.printStackTrace();
            }
        }
	    return salesList.stream().collect(Collectors.toCollection(TreeSet::new));
	}
}
