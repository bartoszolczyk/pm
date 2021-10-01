package com.example.data.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.dialect.lock.OptimisticEntityLockException;

import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import static com.example.commons.exception.messages.SystemExceptionMessage.OBJECT_WAS_MODIFIED_IN_ANOTHER_TRANSACTION;
import static java.lang.String.format;
import static lombok.AccessLevel.NONE;

@Getter
@Setter
@SuperBuilder
@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
public class BaseEntity {

    @Setter(NONE)
    @Version
    @ColumnDefault("0")
    private Long version;

    public void checkVersion(Long version) {
        if (!this.version.equals(version)) {
            throw new OptimisticEntityLockException(
                this,
                format(OBJECT_WAS_MODIFIED_IN_ANOTHER_TRANSACTION.getMessage(), this.getClass().getSimpleName())
            );
        }
    }
}
