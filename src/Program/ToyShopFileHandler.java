package Program;

import Exceptions.ErrorWriteFileException;
import Interfaces.ToyShopFileHandlerInterface;
import ToyData.Toy;

import java.io.File;
import java.io.FileWriter;

/**
 * Класс для работы с файлами.
 */
public class ToyShopFileHandler implements ToyShopFileHandlerInterface {
    private final String fileName;
    private final String folderName;
    private final File file;
    private final File folder;
    public ToyShopFileHandler(String fileName, String folderName) {
        this.fileName = fileName;
        this.folderName = folderName;
        this.folder = new File(folderName);
        this.file = new File(folder, fileName);

        deleteExistFile();
    }

    /**
     * Метод записывает переданные данные в файл
     * Если при записи произошла ошибка, то выбрасывает исключение ErrorWriteFileException.
     *
     * @param toy - данные игрушки для записи в файл
     * @throws ErrorWriteFileException если произошла ошибка записи в файл
     */
    @Override
    public void writeToFile(Toy toy) throws ErrorWriteFileException {
        if (!folder.exists()) {
            folder.mkdirs();
        }

        try (FileWriter writer = new FileWriter(file, true)) {
            writer.write(toy + "\n");
        } catch (Exception e) {
            throw new ErrorWriteFileException("Ошибка записи в файл", file.getAbsolutePath());
        }
    }

    private void deleteExistFile(){
        if(file.isFile()){
            file.delete();
        }
    }
}
