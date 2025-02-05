package util;

import domain.Journal;
import domain.entity.JournalEntity;

public class JournalConverter implements Converter<JournalEntity, Journal> {

    @Override
    public JournalEntity convertDoMainToEntity(Journal journal) {
        return JournalEntity.builder()
                .id(journal.getId())
                .name(journal.getName())
                .description(journal.getDescription())
                .date(journal.getDate())
                .build();
    }

    @Override
    public Journal convertEntityToDoMain(JournalEntity journalEntity) {
        return Journal.builder()
                .id(journalEntity.getId())
                .name(journalEntity.getName())
                .description(journalEntity.getDescription())
                .date(journalEntity.getDate())
                .build();
    }
}
